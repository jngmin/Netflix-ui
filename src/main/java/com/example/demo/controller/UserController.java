package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.client.UserClient;
import com.example.demo.dto.user.CheckPasswordRequest;
import com.example.demo.dto.user.LoginRequest;
import com.example.demo.dto.user.RegisterRequest;
import com.example.demo.dto.user.UserResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserClient userClient;

    // 로그인 페이지
    @GetMapping("/login")
    public String loginPage() {
        return "common/login/LoginPage"; // Thymeleaf 경로
    }

    // 로그인 Ajax 요청 처리
    @ResponseBody
    @PostMapping("/login")
    public UserResponse login(@RequestBody LoginRequest loginForm, HttpServletRequest request) {
    	
    	UserResponse user = userClient.login(loginForm);
        
        HttpSession session = request.getSession(true);
        session.setAttribute("id", user.getId());
        session.setAttribute("username", user.getUsername());
        session.setAttribute("type", user.getType());
        // 필요한 정보는 추가로 세션에 저장

        return user;
    }
    
    // 로그아웃 처리
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

    // 회원가입 페이지
    @GetMapping("/join")
    public String joinPage() {
        return "common/login/JoinPage";
    }

    // 회원가입 처리
    @PostMapping("/join/new")
    public String join(@ModelAttribute RegisterRequest registerRequest) {
        userClient.register(registerRequest);
        return "redirect:/login"; // 회원가입 후 로그인 페이지로 리다이렉트
    }

    // 분기 처리 페이지 (기존 classify 대응)
    @GetMapping("/classify")
    public String classify() {
        return "redirect:/";
    }
    
    // 비밀번호 확인
    @ResponseBody
    @PostMapping("/checkPassword")
    public Boolean checkPassword(@RequestParam Long id, @RequestParam String password) {
        CheckPasswordRequest requestDto = new CheckPasswordRequest(id, password);
        return userClient.checkPassword(requestDto);
    }
}

