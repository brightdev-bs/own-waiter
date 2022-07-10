package vanilla.ownwaiter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vanilla.ownwaiter.entity.Restaurant;
import vanilla.ownwaiter.entity.user.User;
import vanilla.ownwaiter.service.RestaurantService;

@Controller
@RequestMapping("/manager")
@RequiredArgsConstructor
public class ManagerController {

    private final RestaurantService restaurantService;

    @GetMapping
    public String loadMainPage() {
        return "manager/homeManager";
    }

    @GetMapping("/manager/foodList")
    public String moveToFoodManage(Authentication auth, Model model) {
        User manager = (User) auth.getDetails();
        Restaurant findRes = restaurantService.findById(manager.getId());
        model.addAttribute("items", findRes.getItems());
        return "/manager/foodList";
    }

    @GetMapping("/manager/orderList")
    public String moveToOrderList(Authentication auth, Model model) {
        User manager = (User) auth.getDetails();
        Restaurant findRes = restaurantService.findById(manager.getId());
//        model.addAttribute("items", findRes.getItems());
        return "/manager/orerList";
    }


}
