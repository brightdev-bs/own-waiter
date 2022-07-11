package vanilla.ownwaiter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class RestaurantController {

//    private final RestaurantService restaurantService;
//
//    @GetMapping("/res/{restaurantId}")
//    public String moveToRestaurantPage(@PathVariable Long restaurantId, Model model) {
//        Restaurant findRes = restaurantService.findById(restaurantId);
//        model.addAttribute("restaurant", findRes);
//        return "/restaurant";
//    }
//
//    @GetMapping(value = {"/{id}/{restaurantId}/{foodId}"})
//    public String addToBasket(@PathVariable Long id,
//                              @PathVariable Long restaurantId,
//                              @PathVariable Long foodId,
//                              Model model) {
//
//        if(id == null)
//            return "/login/joinForm";
//
//        Basket basket = new Basket(id);
//        model.addAttribute("basket", basket);
//
//        return "redirect:/";
//    }

}
