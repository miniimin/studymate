package com.example.studymate.User.dto;

import com.example.studymate.User.entity.User;
import lombok.Getter;

@Getter
public class AddUserRequest {
    private String email;
    private String nickname;
    private String password;

//    public User toEntity() {
//        return User.builder()
//                .email(email)
//                .nickname(nickname)
//                .password(password)
//                .build();
//    }
}
