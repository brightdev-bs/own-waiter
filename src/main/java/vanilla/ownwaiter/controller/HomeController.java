package vanilla.ownwaiter.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import vanilla.ownwaiter.domain.Restaurant;
import vanilla.ownwaiter.service.RestaurantService;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomeController {
    private final RestaurantService restaurantService;

    @ModelAttribute
    public Restaurant restaurant() {
        return new Restaurant();
    }

    @GetMapping
    public String main(Model model) {

        initData(model);

        return "/home";
    }

    private void initData(Model model) {
        List<Restaurant> restaurants = getRestaurants();
        model.addAttribute("restaurants", restaurants);
    }

    private List<Restaurant> getRestaurants() {
        return restaurantService.findAll();
    }
}
