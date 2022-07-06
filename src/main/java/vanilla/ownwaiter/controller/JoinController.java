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
import vanilla.ownwaiter.constant.SessionConst;
import vanilla.ownwaiter.domain.login.JoinForm;
import vanilla.ownwaiter.domain.user.SiteUser;
import vanilla.ownwaiter.domain.user.SiteUserRole;
import vanilla.ownwaiter.domain.user.SiteUserSex;
import vanilla.ownwaiter.service.SiteUserService;
import vanilla.ownwaiter.validator.JoinValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("/join")
@RequiredArgsConstructor
public class JoinController {

    private final SiteUserService siteUserService;

    private final JoinValidator joinValidator;

    @GetMapping
    public String loadJoinForm(Model model) {
        model.addAttribute(new JoinForm());
        model.addAttribute("userSex", SiteUserSex.values());
        model.addAttribute("userRole", SiteUserRole.values());
        return "login/joinForm";
    }

    @PostMapping
    public String joinUser(@Validated @ModelAttribute JoinForm joinForm, BindingResult bindingResult, HttpServletRequest request) {

        joinValidator.validate(joinForm, bindingResult);

        if(bindingResult.hasErrors()) {
            log.info("joinUser errors = {}", bindingResult);
            return "login/joinForm";
        }

        SiteUser joinSiteUser = new SiteUser(joinForm.getUsername(), joinForm.getEmail(), joinForm.getPwd(),
                joinForm.getSiteUserSex(joinForm.getSex()), joinForm.getSiteUserRole(joinForm.getRole()));
        siteUserService.save(joinSiteUser);

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.MEMBER_ID, joinSiteUser.getId());

        return "redirect:/login";
    }
}
