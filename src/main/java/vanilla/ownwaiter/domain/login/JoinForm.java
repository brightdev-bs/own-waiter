package vanilla.ownwaiter.domain.login;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vanilla.ownwaiter.domain.user.UserRole;
import vanilla.ownwaiter.domain.user.UserSex;

@Slf4j
@Data
@Component
public class JoinForm {
    String username;
    String email;
    String pwd;
    String pwd2;
    String sex;
    String role;

    public UserRole getSiteUserRole(String role) {
        if(role.equals(UserRole.USER.getRole())) {
            return UserRole.USER;
        } else {
            return UserRole.ADMIN;
        }
    }

    public UserSex getSiteUserSex(String sex) {
        if(sex.equals(UserSex.MAN.getSex())) {
            return UserSex.MAN;
        } else {
            return UserSex.WOMAN;
        }
    }
}
