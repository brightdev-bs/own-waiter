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
import vanilla.ownwaiter.entity.dto.RestaurantForm;
import vanilla.ownwaiter.entity.user.User;
import vanilla.ownwaiter.service.RestaurantService;
import vanilla.ownwaiter.service.UserService;

import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/manager")
@RequiredArgsConstructor
public class ManagerController {

    private final RestaurantService restaurantService;
    private final UserService userService;

    @GetMapping
    public String loadMainPage() {

        return "manager/homeManager";
    }

    @GetMapping("/foodList")
    public String moveToFoodList(Authentication auth, Model model) {
        User manager = getUser(auth);
        model.addAttribute("foods", manager.getRestaurant().getFoods());
        return "/manager/foodList";
    }

    @GetMapping("orderList")
    public String moveToOrderList(Authentication auth, Model model) {
        User manager = getUser(auth);
        Restaurant restaurant = manager.getRestaurant();
        model.addAttribute("restaurant", restaurant);
        return "/manager/orderList";
    }

    @GetMapping("/register/restaurant")
    public String registerRestaurant(Model model) {
        model.addAttribute("restaurantForm", new RestaurantForm());
        return "/manager/registerRestaurant";
    }

    @PostMapping("/register/restaurant")
    public String registerRestaurant(@ModelAttribute RestaurantForm restaurantForm, Authentication auth) throws IOException {
        Restaurant res = restaurantService.uploadProfileAndSetImg(restaurantForm.toEntity(), restaurantForm.getImg());

        User user = getUser(auth);
        user.registerRestaurant(res);
        userService.save(user);

        return "redirect:/manager";
    }

    @GetMapping("/register/food")
    public String moveToRegisterFood() {
        return "/manager/registerFood";
    }

    private User getUser(Authentication auth) {
        User user = (User) auth.getPrincipal();
        return user;
    }



}
