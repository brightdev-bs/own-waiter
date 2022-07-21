package vanilla.ownwaiter.entity.dto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import vanilla.ownwaiter.entity.user.User;
import vanilla.ownwaiter.entity.user.UserRole;
import vanilla.ownwaiter.entity.user.UserSex;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Slf4j
@Data
public class JoinForm {
    @NotBlank
    String username;
    @NotBlank
    String email;
    @NotBlank
    String pwd;
    @NotBlank
    String pwd2;
    String sex;
    String role;

    public UserRole getUserRole(String role) {
        if(role.equals(UserRole.USER.getRole())) {
            return UserRole.USER;
        } else {
            return UserRole.ADMIN;
        }
    }

    public UserSex getUserSex(String sex) {
        if(sex.equals(UserSex.MAN.getSex())) {
            return UserSex.MAN;
        } else {
            return UserSex.WOMAN;
        }
    }

    public User toEntity(JoinForm joinForm) {
        return User.builder()
                .username(joinForm.getUsername())
                .email(joinForm.getEmail())
                .password(joinForm.getPwd())
                .sex(joinForm.getUserSex(joinForm.getSex()))
                .role(joinForm.getUserRole(joinForm.getRole()))
                .build();
    }
}
