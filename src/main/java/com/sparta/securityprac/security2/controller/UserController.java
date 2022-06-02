package com.sparta.securityprac.security2.controller;

import com.sparta.securityprac.security2.requestdto.SignupRequestDto;
import com.sparta.securityprac.security2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor // 생성자를 생성, 이 어노테이션으로 아래 주석이 달린 생성자를 생략가능하다.
@Controller
public class UserController {

    private final UserService userService;

//    @Autowired
//    public UserController(UserService userService){
//        this.userService = userService;
//    }

    //로그인 페이지
    @GetMapping("/user/loginView")
    public String login(){
        return "login";
    }

    //회원가입 페이지
    @GetMapping("/user/signup")
    public String signup(){
        return "signup";
    }

    //회원가입 처리
    @PostMapping("/user/signup")
    public String registerUser(SignupRequestDto requestDto){
        userService.registerUser(requestDto);
        return "redirect:/user/loginView";
    }


}
