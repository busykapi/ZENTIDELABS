package com.zentidelabs.web.controller.customer.reserve;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReserveController {
    @GetMapping("/works")
    public String works(){
        return "works";
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }

    @GetMapping("/contact")
    public String contact(){
        return "contact";
    }

    @GetMapping("/out")
    public String out(){
        return "out";
    }
}
