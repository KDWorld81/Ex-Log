package com.exlog.exlog.pagecontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StartPageController {

    @GetMapping("/exlog")
    public String startPage() {
        return "exlog";
    }
}
