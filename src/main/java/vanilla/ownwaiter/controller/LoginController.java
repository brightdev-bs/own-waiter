package vanilla.ownwaiter.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import vanilla.ownwaiter.domain.login.LoginForm;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    @ModelAttribute
    public LoginForm loginForm() {
        return new LoginForm();
    }

    @GetMapping("/login")
    public String loadLoginForm() {
        return "login/loginForm";
    }
}
