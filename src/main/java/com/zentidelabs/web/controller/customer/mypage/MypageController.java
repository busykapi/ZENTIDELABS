package com.zentidelabs.web.controller.customer.mypage;

import com.zentidelabs.web.model.MemberVO;
import com.zentidelabs.web.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MypageController {

    private final UserService service;

    @GetMapping("")
    public String mypage(MemberVO memberVO, Model model){
        MemberVO member = service.getMemberInfo(memberVO);
        model.addAttribute("member",member);
        System.out.println(member);
        return "mypage";
    }

    @GetMapping("/mylist")
    public String mylist(){

        return "mylist";
    }

    @GetMapping("/myreserve")
    public String myreserve(){

        return "reserve";
    }

}
