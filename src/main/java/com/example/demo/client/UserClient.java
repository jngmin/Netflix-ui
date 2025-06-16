package com.example.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.user.CheckPasswordRequest;
import com.example.demo.dto.user.LoginRequest;
import com.example.demo.dto.user.RegisterRequest;
import com.example.demo.dto.user.UserInfoResponse;
import com.example.demo.dto.user.UserResponse;
import com.example.demo.dto.user.UserUpdateRequest;

@FeignClient(name = "login-api", url = "http://netflixclone-service:8082/api/users")
public interface UserClient {

    // 아이디 중복 확인
    @GetMapping("/check-username")
    Boolean existsByUsername(@RequestParam("username") String username);

    // 회원가입
    @PostMapping("/register")
    UserResponse register(@RequestBody RegisterRequest requestDto);

    // 로그인
    @PostMapping("/login")
    UserResponse login(@RequestBody LoginRequest requestDto);

    // 로그아웃
    @PostMapping("/logout")
    void logout();
    
    // 비밀번호 확인
    @PostMapping("/check-password")
    public Boolean checkPassword(@RequestBody CheckPasswordRequest requestDto);

    // 사용자 정보 조회 (마이페이지)
    @GetMapping("/{id}/mypage")
    UserInfoResponse getUserById(@PathVariable("id") Long id);

    // 사용자 정보 수정
    @PutMapping("/{id}")
    UserResponse updateUser(@PathVariable("id") Long id, @RequestBody UserUpdateRequest requestDto);

    // 회원 탈퇴
    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable("id") Long id);
}
