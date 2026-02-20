package com.exlog.exlog.pagecontroller.mypage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogHistoryPageController {

    @GetMapping("/mypage/history")
    public String historyPage(){ return "mypage/history"; }

}

