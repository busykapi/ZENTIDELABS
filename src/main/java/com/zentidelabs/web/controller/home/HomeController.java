package com.zentidelabs.web.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(){

        return "index";
    }


    @GetMapping("/do")
    public String dp(Model model) {
        model.addAttribute("name","Hello");
        return "do";
    }
}
