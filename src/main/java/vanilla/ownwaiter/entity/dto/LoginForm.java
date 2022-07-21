package vanilla.ownwaiter.entity.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class LoginForm {
    @Email
    String email;
    @NotBlank
    String password;

}
