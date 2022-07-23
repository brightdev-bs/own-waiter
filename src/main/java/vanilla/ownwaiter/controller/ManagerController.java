package vanilla.ownwaiter.controller;

import com.google.zxing.WriterException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
import vanilla.ownwaiter.entity.food.FoodCategory;
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
    public String loadPage(@AuthenticationPrincipal User user, Model model) {
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
    public String registerRestaurant(@ModelAttribute RestaurantRegisterForm restaurantRegisterForm,
                                     @AuthenticationPrincipal User user) throws IOException {
        String uploadUrl = s3Uploader.upload(restaurantRegisterForm.getImg(), "restaurant");

        Restaurant restaurant = restaurantService.setImg(restaurantRegisterForm.toEntity(), uploadUrl);
        restaurantService.saveWithQr(restaurant);

        user.registerRestaurant(restaurant);
        userRepository.save(user);

        return "redirect:/manager";
    }

    @GetMapping("/foodList")
    public String moveToFoodList(@AuthenticationPrincipal User user, Model model) {
        user = userRepository.findById(user.getId()).orElseThrow(() -> new NoSuchElementException("찾을 수 없습니다."));
        model.addAttribute("foods", user.getRestaurant().getFoods());
        return "/manager/foodList";
    }

    @GetMapping("/register/food")
    public String moveToRegisterFood(Model model) {
        model.addAttribute("foodRegisterForm", new FoodRegisterForm());
        model.addAttribute("foodCategory", FoodCategory.values());
        return "manager/registerFoodForm";
    }

    @PostMapping("/register/food")
    public String registerFood(@ModelAttribute FoodRegisterForm foodRegisterForm,
                               @AuthenticationPrincipal User user) throws IOException {
        String uploadUrl = s3Uploader.upload(foodRegisterForm.getImg(), "food");

        user = userRepository.findById(user.getId()).orElseThrow(() -> new NoSuchElementException("없는 유저입니다."));
        Restaurant restaurant = user.getRestaurant();

        Food food = foodRegisterForm.toEntity(foodRegisterForm, uploadUrl);
        food.setRestaurant(restaurant);
        foodRepository.save(food);

        return "redirect:/manager/foodList";
    }

    @GetMapping("orderList")
    public String moveToOrderList(@AuthenticationPrincipal User manager, Model model) {
        Restaurant restaurant = manager.getRestaurant();
        model.addAttribute("orders", restaurant.getOrders());
        return "/manager/orderList";
    }

    @GetMapping("qr")
    public String moveToRestaurantQr(@AuthenticationPrincipal User manager, Model model) {
        Restaurant restaurant = manager.getRestaurant();
        model.addAttribute("qr", restaurant.getQrCodeUrl());
        return "/manager/restaurantQr";
    }


    private void addRestaurantFormToModel(Model model) {
        model.addAttribute("restaurantRegisterForm", new RestaurantRegisterForm());
    }
}
