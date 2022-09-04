package vanilla.ownwaiter.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import vanilla.ownwaiter.entity.restaurant.Restaurant;

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
        return "redirect:home";
    }

    @GetMapping("/home")
    public String main() {
        return "home";
    }
}
