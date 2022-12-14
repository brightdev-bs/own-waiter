package vanilla.ownwaiter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vanilla.ownwaiter.entity.restaurant.Restaurant;
import vanilla.ownwaiter.entity.user.User;
import vanilla.ownwaiter.service.RestaurantService;
import vanilla.ownwaiter.service.UserService;

import java.util.List;

@Controller
@RequestMapping("customer/search")
@RequiredArgsConstructor
public class SearchController {

    private final RestaurantService restaurantService;
    private final UserService userService;

    @GetMapping
    public String moveToSearch(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("histories", userService.sortSearchHistory(user));
        return "customer/search/searchRestaurant";
    }

    @GetMapping("/restaurant")
    public String loadRestaurant(@RequestParam("input") String input,
                                 @AuthenticationPrincipal User user, Model model) {
        userService.addSearchHistory(user.getId(), input);
        List<Restaurant> restaurants = restaurantService.likeByKeyword(input);
        model.addAttribute("restaurants", restaurants);
        return "customer/search/searchRestaurantResult";
    }

    @GetMapping("/restaurant/food")
    public String loadFood(@RequestParam("restaurantId") Long restaurantId, Model model) {
        Restaurant res = restaurantService.findById(restaurantId);
        model.addAttribute("foods", res.getFoods());
        return "customer/search/restaurantFoodList";
    }
}
