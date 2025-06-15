package com.example.demo.dto.user;

import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotBlank;

import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginRequest {
	
	@NotBlank(message = "아이디를 입력해 주세요.")
	private String username;
	
	@NotBlank(message = "비밀번호를 입력해 주세요.")
	private String password;
}
