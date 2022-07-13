package vanilla.ownwaiter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vanilla.ownwaiter.entity.Restaurant;
import vanilla.ownwaiter.service.RestaurantService;

import java.util.List;

@Controller
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {

    private final RestaurantService restaurantService;

    @GetMapping
    public String moveToSearch() {
        return "/search/searchRestaurant";
    }

    @GetMapping("/restaurant")
    public String loadRestaurant(@RequestParam("input") String input, Model model) {
        List<Restaurant> restaurants = restaurantService.likeByKeyword(input);
        model.addAttribute("restaurants", restaurants);
        return "search/searchRestaurantResult";
    }

    @GetMapping("/restaurant/food")
    public String loadFood(@RequestParam("restaurantId") Long restaurantId, Model model) {
        System.out.println("SearchController.loadFood");
        Restaurant res = restaurantService.findById(restaurantId);
        model.addAttribute("foods", res.getFoods());
        return "/search/restaurantFoodList";
    }
}
