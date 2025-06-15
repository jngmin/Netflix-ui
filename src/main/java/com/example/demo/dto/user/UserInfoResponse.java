package com.example.demo.dto.user;

import com.example.demo.entity.*;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResponse {

	private String username;
	private String name;
	private String address;
	private int age;
	
	public UserInfoResponse(User user) {
		this.username = user.getUsername();
		this.name = user.getName();
		this.address = user.getAddress();
		this.age = user.getAge();
	}
}