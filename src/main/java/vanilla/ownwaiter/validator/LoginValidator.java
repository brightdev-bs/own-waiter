package vanilla.ownwaiter.validator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import vanilla.ownwaiter.domain.user.User;
import vanilla.ownwaiter.domain.login.LoginForm;
import vanilla.ownwaiter.service.UserService;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoginValidator implements Validator {

    private final UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return LoginForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LoginForm loginForm = (LoginForm) target;

        User loginSiteUser = userService.findByEmail(loginForm.getEmail());
        if(loginSiteUser == null) {
            errors.rejectValue("email", "notExist");
            return;
        }

        if (!loginSiteUser.getPassword().equals(loginForm.getPassword())) {
            log.info("비밀번호 불일치");
            errors.rejectValue("pwd", "notMatch");
            return;
        }
    }
}
