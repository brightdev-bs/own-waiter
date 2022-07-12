package vanilla.ownwaiter.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vanilla.ownwaiter.entity.Restaurant;
import vanilla.ownwaiter.entity.dto.FoodRegisterForm;
import vanilla.ownwaiter.entity.dto.RestaurantRegisterForm;
import vanilla.ownwaiter.entity.food.Food;
import vanilla.ownwaiter.entity.user.User;
import vanilla.ownwaiter.file.S3Uploader;
import vanilla.ownwaiter.repository.FoodRepository;
import vanilla.ownwaiter.service.RestaurantService;
import vanilla.ownwaiter.service.UserService;

import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/manager")
@RequiredArgsConstructor
public class ManagerController {

    private final RestaurantService restaurantService;
    private final FoodRepository foodRepository;
    private final UserService userService;
    private final S3Uploader s3Uploader;

    @GetMapping
    public String loadPage(Authentication auth, Model model) {
        User user = getUser(auth);
        if(user.getRestaurant() == null) {
            addRestaurantFormToModel(model);
            return "redirect:/manager/register/restaurant";
        }

        return "manager/homeManager";
    }

    @GetMapping("/foodList")
    public String moveToFoodList(Authentication auth, Model model) {
        User manager = getUser(auth);
        model.addAttribute("foods", manager.getRestaurant().getFoods());
        return "/manager/foodList";
    }

    @GetMapping("/register/food")
    public String moveToRegisterFood(Model model) {
        model.addAttribute("foodRegisterForm", new FoodRegisterForm());
        return "manager/registerFoodForm";
    }

    @PostMapping("/register/food")
    public String registerFood(@ModelAttribute FoodRegisterForm foodRegisterForm, Authentication auth) throws IOException {
        String uploadUrl = s3Uploader.upload(foodRegisterForm.getImg(), "food");

        User user = getUser(auth);
        Restaurant restaurant = user.getRestaurant();

        Food food = foodRegisterForm.toEntity(foodRegisterForm, uploadUrl);
        food.setRestaurant(restaurant);
        foodRepository.save(food);

        return "redirect:/manager/foodList";
    }

    @GetMapping("orderList")
    public String moveToOrderList(Authentication auth, Model model) {
        User manager = getUser(auth);
        Restaurant restaurant = manager.getRestaurant();
        model.addAttribute("orders", restaurant.getOrders());
        return "/manager/orderList";
    }

    @GetMapping("/register/restaurant")
    public String registerRestaurant(Model model) {
        addRestaurantFormToModel(model);
        return "/manager/registerRestaurantForm";
    }

    @PostMapping("/register/restaurant")
    public String registerRestaurant(@ModelAttribute RestaurantRegisterForm restaurantRegisterForm, Authentication auth) throws IOException {
        String uploadUrl = s3Uploader.upload(restaurantRegisterForm.getImg(), "restaurant");
        User user = getUser(auth);

        Restaurant restaurant = restaurantService.setImg(restaurantRegisterForm.toEntity(), uploadUrl);
        user.registerRestaurant(restaurant);

        userService.save(user);

        return "redirect:/manager";
    }

    private void addRestaurantFormToModel(Model model) {
        model.addAttribute("restaurantRegisterForm", new RestaurantRegisterForm());
    }

    private User getUser(Authentication auth) {
        User user = (User) auth.getPrincipal();
        return user;
    }
}
