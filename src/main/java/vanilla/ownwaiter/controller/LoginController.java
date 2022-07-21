package vanilla.ownwaiter.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import vanilla.ownwaiter.entity.dto.LoginForm;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {
    @GetMapping("/login")
    public String loadLoginForm(@RequestParam(value = "error", required = false) String error,
                                @RequestParam(value = "exception", required = false) String exception,
                                @ModelAttribute LoginForm loginForm, Model model) {

        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "login/loginForm";
    }
}
