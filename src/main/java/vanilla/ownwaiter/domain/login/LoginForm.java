package vanilla.ownwaiter.domain.login;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class LoginForm {
    String email;
    String password;

}
