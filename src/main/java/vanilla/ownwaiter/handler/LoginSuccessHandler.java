package vanilla.ownwaiter.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import vanilla.ownwaiter.entity.user.User;
import vanilla.ownwaiter.entity.user.UserRole;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        User user = (User) authentication.getPrincipal();
        UserRole role = user.getRole();
        if(role == UserRole.USER) {
            response.sendRedirect("/home");
        }

        if(role == UserRole.ADMIN) {

            if (user.getRestaurant() == null) {
                response.sendRedirect("/manager/register/restaurant");
                return;
            }

            response.sendRedirect("/manager");
        }
    }
}
