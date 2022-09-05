package vanilla.ownwaiter.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vanilla.ownwaiter.entity.Order;
import vanilla.ownwaiter.entity.basket.Basket;
import vanilla.ownwaiter.entity.restaurant.Restaurant;
import vanilla.ownwaiter.entity.user.User;
import vanilla.ownwaiter.service.BasketFoodService;
import vanilla.ownwaiter.service.OrderService;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final BasketFoodService basketFoodService;
    private final OrderService orderService;

    @PostMapping("/submit")
    public String completeOrder(@AuthenticationPrincipal User user, @RequestParam String request) {
        Basket basket = basketFoodService.getBasket(user);
        Restaurant restaurant = basket.getRestaurant();
        Order order = new Order(restaurant, basket, request);
        orderService.save(order, basket.getBasketFoods());
        return "redirect:/home";
    }

}
