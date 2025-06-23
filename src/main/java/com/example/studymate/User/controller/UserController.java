package com.example.studymate.User.controller;

import com.example.studymate.User.dto.AddUserRequest;
import com.example.studymate.User.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/api/user")
    public ResponseEntity<String> join(@RequestBody AddUserRequest request) {
        userService.join(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입 완료");
    }

}
