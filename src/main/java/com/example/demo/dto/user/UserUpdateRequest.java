package com.example.demo.dto.user;

import com.example.demo.entity.*;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserUpdateRequest {

	private String name;
	private String address;
	private int age;
	private String password;

	public User toEntity(User user) {
		user.setName(this.name);
		user.setAddress(this.address);
		user.setAge(this.age);
		user.setPassword(this.password);
		return user;
	}
}