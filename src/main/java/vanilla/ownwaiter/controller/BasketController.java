package vanilla.ownwaiter.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vanilla.ownwaiter.entity.basket.Basket;
import vanilla.ownwaiter.entity.basket.BasketFood;
import vanilla.ownwaiter.entity.user.User;
import vanilla.ownwaiter.service.BasketFoodService;


import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/basket")
public class BasketController {

    private final BasketFoodService basketFoodService;

    @GetMapping
    public String moveToBasket(@AuthenticationPrincipal User user, Model model) {
        List<BasketFood> basketFoods = basketFoodService.getBasketFood(user);
        model.addAttribute("basketFoods", basketFoods);
        return "/customer/basket";
    }

    @PostMapping("/add")
    public String addFoodToBasket(@AuthenticationPrincipal User user, @RequestParam String id) {
        basketFoodService.save(user, id);
        return "redirect:/basket";
    }


}