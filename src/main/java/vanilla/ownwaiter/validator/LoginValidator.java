package vanilla.ownwaiter.validator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import vanilla.ownwaiter.entity.user.User;
import vanilla.ownwaiter.entity.dto.LoginForm;
import vanilla.ownwaiter.service.UserService;

import java.util.Optional;

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

        Optional<User> user = userService.findByEmail(loginForm.getEmail());
        if(user.isPresent()) {
            errors.rejectValue("email", "notExist");
            return;
        }

        String password = user.get().getPassword();
        if (!password.equals(loginForm.getPassword())) {
            errors.rejectValue("pwd", "notMatch");
            return;
        }
    }
}
