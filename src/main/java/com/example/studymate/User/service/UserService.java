package com.example.studymate.User.service;

import com.example.studymate.User.dto.AddUserRequest;
import com.example.studymate.User.entity.User;
import com.example.studymate.User.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void join(AddUserRequest request) {

        String encodedPassword = bCryptPasswordEncoder.encode(request.getPassword());
        User user = User.builder()
                .email(request.getEmail())
                .password(encodedPassword)
                .nickname(request.getNickname())
                .build();

        userRepository.save(user);
    }
}
