package com.zentidelabs.web.controller.customer.login;

import com.zentidelabs.web.model.MemberVO;
import com.zentidelabs.web.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@Slf4j
//@SessionAttributes("memberVO")
@RequiredArgsConstructor
public class LoginController {

    private final UserService service;

    @GetMapping("/login")
    public String login(){

        return "login";
    }

    @PostMapping("/login")
    public String login(MemberVO memberVO, HttpSession session, Model model) {

        MemberVO loginUser = service.getLogin(memberVO);

        if (loginUser==null){
            log.info("로그인 실패");

        } else {  //성공시 세션에 userId를 넣어줌
            log.info("로그인 성공");

            session.setAttribute("userId",loginUser.getUserId());
            session.setMaxInactiveInterval(1800); //30분동안 로그인유효
        }

        model.addAttribute("loginUser",loginUser);
        return "login";
    }


    @GetMapping("/logout")
    public String out(HttpServletRequest request, Model model){
        request.getSession().invalidate();
        log.info("로그아웃");

        return "redirect:/";
    }


    @GetMapping("/join")
    public String join(MemberVO memberVO ,Model model){
        model.addAttribute("memberVO",memberVO);

        return "join";
    }
}
