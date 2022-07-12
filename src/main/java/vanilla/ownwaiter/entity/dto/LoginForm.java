package vanilla.ownwaiter.entity.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class LoginForm {
    String email;
    String password;

}
