package com.example.studymate.User.controller;

import com.example.studymate.User.dto.AddUserRequest;
import com.example.studymate.User.entity.User;
import com.example.studymate.User.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    
    // 회원가입
    @PostMapping("/api/users")
    public ResponseEntity<String> join(@RequestBody AddUserRequest request) {
        userService.join(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입 완료");
    }

    // 내 정보 조회
    @GetMapping("/api/users/me")
    public ResponseEntity<Map<String, Object>> getUserInfo(@AuthenticationPrincipal User user) {
        Map<String, Object> response = new HashMap<>();
        if (user == null){
            response.put("isLoggedIn", false);
        } else {
            response.put("nickname", user.getNickname());
            response.put("isLoggedIn", true);
        }
        System.out.println("유저 정보: " + user);
        System.out.println("응답 정보: " + response);
        return ResponseEntity.ok(response);
    }

}
