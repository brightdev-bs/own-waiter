package vanilla.ownwaiter.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vanilla.ownwaiter.entity.user.User;
import vanilla.ownwaiter.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.NoSuchElementException;

@Slf4j
@Controller
@RequestMapping("/qr")
@RequiredArgsConstructor
public class QrController {

    @GetMapping("/scan")
    public String moveToScan() {
        return "customer/qr/scanQr";
    }


}
