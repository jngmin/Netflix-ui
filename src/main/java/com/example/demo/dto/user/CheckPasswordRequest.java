package com.example.demo.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CheckPasswordRequest {

	private Long id;
	private String password;
}