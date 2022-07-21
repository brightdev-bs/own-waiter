package vanilla.ownwaiter.validator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import vanilla.ownwaiter.entity.dto.JoinForm;
import vanilla.ownwaiter.entity.user.User;
import vanilla.ownwaiter.service.UserService;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class JoinValidator implements Validator {

    private final UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return JoinForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        JoinForm joinForm = (JoinForm) target;

        if(!validateEmail(joinForm)) {
            errors.rejectValue("email", "duplicated");
            return;
        }

        if(!validatePassword(joinForm)) {
            errors.rejectValue("pwd2", "notMatch");
            return;
        }

        if(!validateName(joinForm)) {
            errors.rejectValue("username", "duplicated");
            return;
        }

    }

    private boolean validateEmail(JoinForm form) {
        Optional<User> user = userService.findByEmail(form.getEmail());
        if(user.isPresent()) {
            return false;
        }
        return true;
    }

    private boolean validatePassword(JoinForm form) {
        return form.getPwd().equals(form.getPwd2());
    }

    private boolean validateName(JoinForm form) {
        Optional<User> user = userService.findByUsername(form.getUsername());
        if(user.isPresent()) {
            return false;
        }
        return true;
    }
}
