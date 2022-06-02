package com.sparta.securityprac.security2.controller;

import com.sparta.securityprac.security2.model.UserRoleEnum;
import com.sparta.securityprac.security2.requestdto.SignupRequestDto;
import com.sparta.securityprac.security2.requestdto.UserInfoDto;
import com.sparta.securityprac.security2.security.UserDetailsImpl;
import com.sparta.securityprac.security2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    // 회원 관련 정보 받기
    @PostMapping("/user/userinfo")
    @ResponseBody
    public UserInfoDto getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        String username = userDetails.getUser().getUsername();

        return new UserInfoDto(username);
    }
}
