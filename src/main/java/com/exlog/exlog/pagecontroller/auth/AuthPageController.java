package com.exlog.exlog.pagecontroller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthPageController {

    @GetMapping("/signup")
    public String signupPage(){
        return "auth/signup";
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

}
