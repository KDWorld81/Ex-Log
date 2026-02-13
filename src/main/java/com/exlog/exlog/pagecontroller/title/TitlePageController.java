package com.exlog.exlog.pagecontroller.title;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TitlePageController {

    @GetMapping("/titles")
    public String titles(){
        return "title/titles";
    }
}
