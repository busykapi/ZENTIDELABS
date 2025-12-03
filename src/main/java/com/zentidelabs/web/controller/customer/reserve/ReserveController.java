package com.zentidelabs.web.controller.customer.reserve;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReserveController {
    @GetMapping("/overseas")
    public String overseas(){
        return "overseas";
    }

    @GetMapping("/local")
    public String local(){
        return "local";
    }

    @GetMapping("/reserve")
    public String reserve(){
        return "reserve";
    }

    @GetMapping("/calendar")
    public String calendar(){
        return "calendar";
    }

}
