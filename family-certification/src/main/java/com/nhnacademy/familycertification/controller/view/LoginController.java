package com.nhnacademy.familycertification.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/userLogin")
    public String login() {
        return "login";
    }
    @GetMapping("/logout")
    public String logout() {
        return null;
    }

}
