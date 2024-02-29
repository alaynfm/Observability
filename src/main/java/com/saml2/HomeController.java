package com.saml2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.security.Principal;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/loginSuccess")
    public String loginSuccess(Principal principal) {
        // Puedes agregar más lógica aquí si necesitas procesar los datos del usuario
        return "loginSuccess";
    }
}
