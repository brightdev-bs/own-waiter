package vanilla.ownwaiter.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vanilla.ownwaiter.entity.dto.JoinForm;
import vanilla.ownwaiter.entity.user.UserRole;
import vanilla.ownwaiter.entity.user.UserSex;
import vanilla.ownwaiter.service.UserService;
import vanilla.ownwaiter.validator.JoinValidator;

@Slf4j
@Controller
@RequestMapping("/join")
@RequiredArgsConstructor
public class JoinController {

    private final UserService userService;
    private final JoinValidator joinValidator;

    @GetMapping
    public String loadJoinForm(Model model) {
        model.addAttribute(new JoinForm());
        addModelForEnum(model);
        return "login/joinForm";
    }

    @PostMapping
    public String joinUser(@Validated @ModelAttribute JoinForm joinForm, BindingResult bindingResult, Model model) {

        joinValidator.validate(joinForm, bindingResult);

        if(bindingResult.hasErrors()) {
            log.info("joinUser errors = {}", bindingResult);
            addModelForEnum(model);
            return "login/joinForm";
        }

        userService.save(joinForm);

        return "redirect:login";
    }

    private void addModelForEnum(Model model) {
        model.addAttribute("userSex", UserSex.values());
        model.addAttribute("userRole", UserRole.values());
    }

}
