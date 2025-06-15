package com.example.demo.dto.user;

import com.example.demo.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegisterRequest {

	private String username;
	private String password;
	private String name;
	private String address;
	private int age;

	public User toEntity() {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setName(name);
		user.setAddress(address);
		user.setAge(age);
		user.setType("customer");
		return user;
	}
}