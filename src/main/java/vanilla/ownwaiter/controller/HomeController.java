package vanilla.ownwaiter.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import vanilla.ownwaiter.entity.Restaurant;
import vanilla.ownwaiter.service.RestaurantService;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {
    @ModelAttribute
    public Restaurant restaurant() {
        return new Restaurant();
    }

    @GetMapping("/")
    public String moveToHome() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String main() {
        return "/home";
    }
}
