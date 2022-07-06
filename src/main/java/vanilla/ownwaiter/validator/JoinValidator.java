package vanilla.ownwaiter.validator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import vanilla.ownwaiter.domain.login.JoinForm;
import vanilla.ownwaiter.service.SiteUserService;

@Slf4j
@Component
@RequiredArgsConstructor
public class JoinValidator implements Validator {

    private final SiteUserService siteUserService;

    @Override
    public boolean supports(Class<?> clazz) {
        return JoinForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        JoinForm joinForm = (JoinForm) target;

        if(siteUserService.findByEmail(joinForm.getEmail()) != null) {
            errors.rejectValue("email", "exist");
            return;
        }

        if(!joinForm.getPwd().equals(joinForm.getPwd2())) {
            errors.rejectValue("pwd2", "notMatch");
            return;
        }
    }
}
