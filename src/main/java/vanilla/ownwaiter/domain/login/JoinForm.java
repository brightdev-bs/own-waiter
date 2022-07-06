package vanilla.ownwaiter.domain.login;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vanilla.ownwaiter.domain.user.SiteUserRole;
import vanilla.ownwaiter.domain.user.SiteUserSex;

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

    public SiteUserRole getSiteUserRole(String role) {
        if(role.equals(SiteUserRole.USER.getRole())) {
            return SiteUserRole.USER;
        } else {
            return SiteUserRole.ADMIN;
        }
    }

    public SiteUserSex getSiteUserSex(String sex) {
        if(sex.equals(SiteUserSex.MAN.getSex())) {
            return SiteUserSex.MAN;
        } else {
            return SiteUserSex.WOMAN;
        }
    }
}
