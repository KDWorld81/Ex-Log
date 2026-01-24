package com.exlog.exlog.pagecontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategoryPageController {

    @GetMapping("/category")
    public String categoryPage() {return "exercise/category";}
}
