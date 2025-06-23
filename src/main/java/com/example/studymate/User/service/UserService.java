package com.example.studymate.User.service;

import com.example.studymate.User.dto.AddUserRequest;
import com.example.studymate.User.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void join(AddUserRequest request) {
        userRepository.save(request.toEntity());
    }
}
