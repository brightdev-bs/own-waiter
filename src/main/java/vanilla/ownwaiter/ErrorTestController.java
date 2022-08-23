package vanilla.ownwaiter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
public class ErrorTestController {

    @GetMapping("/error-400")
    public void error400(HttpServletResponse response) throws IOException {
        response.sendError(400, "400 오류");
    }

    @GetMapping("/error-404")
    public void error404(HttpServletResponse response) throws IOException {
        response.sendError(404, "404 오류");
    }

    @GetMapping("/error-500")
    public void error500(HttpServletResponse response) throws IOException {
        log.info("error-500 실행됨.");
        response.sendError(500, "500 오류");
    }

}
