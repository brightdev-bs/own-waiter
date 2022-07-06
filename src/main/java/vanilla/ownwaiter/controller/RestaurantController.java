package vanilla.ownwaiter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import vanilla.ownwaiter.domain.Basket;
import vanilla.ownwaiter.domain.Restaurant;
import vanilla.ownwaiter.service.RestaurantService;

@Controller
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping("/res/{restaurantId}")
    public String moveToRestaurantPage(@PathVariable Long restaurantId, Model model) {
        Restaurant findRes = restaurantService.findById(restaurantId);
        model.addAttribute("restaurant", findRes);
        return "/restaurant";
    }

    @GetMapping(value = {"/{id}/{restaurantId}/{foodId}"})
    public String addToBasket(@PathVariable Long id,
                              @PathVariable Long restaurantId,
                              @PathVariable Long foodId,
                              Model model) {

        if(id == null)
            return "/login/joinForm";

        Basket basket = new Basket(id, restaurantId, foodId);
        model.addAttribute("basket", basket);

        return "redirect:/";
    }

}
