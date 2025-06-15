package com.example.demo.dto.user;

import com.example.demo.entity.User;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor // ✅ 이거 추가
public class UserResponse {

    private Long id;
    private String username;
    private String name;
    private String type;

    public UserResponse(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.name = user.getName();
        this.type = user.getType();
    }
}
