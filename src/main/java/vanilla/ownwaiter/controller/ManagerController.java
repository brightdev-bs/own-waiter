package vanilla.ownwaiter.controller;

import com.google.zxing.WriterException;
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
import vanilla.ownwaiter.repository.UserRepository;
import vanilla.ownwaiter.service.RestaurantService;

import java.io.IOException;
import java.util.NoSuchElementException;

@Slf4j
@Controller
@RequestMapping("/manager")
@RequiredArgsConstructor
public class ManagerController {

    private final RestaurantService restaurantService;
    private final FoodRepository foodRepository;
    private final UserRepository userRepository;
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

    @GetMapping("/register/restaurant")
    public String registerRestaurant(Model model) {
        addRestaurantFormToModel(model);
        return "/manager/registerRestaurantForm";
    }

    @PostMapping("/register/restaurant")
    public String registerRestaurant(@ModelAttribute RestaurantRegisterForm restaurantRegisterForm, Authentication auth) throws IOException, WriterException {
        String uploadUrl = s3Uploader.upload(restaurantRegisterForm.getImg(), "restaurant");

        Restaurant restaurant = restaurantService.setImg(restaurantRegisterForm.toEntity(), uploadUrl);
        restaurantService.saveWithQr(restaurant);

        User user = getUser(auth);
        user.registerRestaurant(restaurant);
        userRepository.save(user);

        return "redirect:/manager";
    }

    @GetMapping("/foodList")
    public String moveToFoodList(Authentication auth, Model model) {
        User user = getUser(auth);
        model.addAttribute("foods", user.getRestaurant().getFoods());
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

    @GetMapping("qr")
    public String moveToRestaurantQr(Authentication auth, Model model) {
        User manager = getUser(auth);
        Restaurant restaurant = manager.getRestaurant();
        model.addAttribute("qr", restaurant.getQrCodeUrl());
        return "/manager/restaurantQr";
    }


    private void addRestaurantFormToModel(Model model) {
        model.addAttribute("restaurantRegisterForm", new RestaurantRegisterForm());
    }

    private User getUser(Authentication auth) {
        User persistence = (User) auth.getPrincipal();
        User user = userRepository.findById(persistence.getId()).orElseThrow(() -> new NoSuchElementException());
        return user;
    }
}
