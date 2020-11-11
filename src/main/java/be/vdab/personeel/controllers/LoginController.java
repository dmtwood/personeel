package be.vdab.personeel.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Dimitri.Gevers@gmail.com
 * @version 1.00 11/10/2020
 * Maps login-requests  ("/login") to login.html
 */
@Controller
@RequestMapping("/login")
class LoginController {

    @GetMapping
    public String login() {
        return "login";
    }
}
