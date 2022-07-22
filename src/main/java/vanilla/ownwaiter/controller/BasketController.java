package vanilla.ownwaiter.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vanilla.ownwaiter.entity.Basket;
import vanilla.ownwaiter.entity.basket.BasketFood;
import vanilla.ownwaiter.service.BasketFoodService;


import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/basket")
public class BasketController {

    private final BasketFoodService basketFoodService;

    @GetMapping
    public String moveToBasket(Authentication auth, Model model) {
        Basket basket = basketFoodService.getBasket(auth);
        List<BasketFood> basketFoods = basket.getBasketFoods();
        model.addAttribute("basketFoods", basketFoods);
        return "/customer/basket";
    }

    @PostMapping("/add")
    public String addFoodToBasket(@RequestParam String id, Authentication auth) {
        basketFoodService.save(id, auth);
        return "redirect:/basket";
    }


}