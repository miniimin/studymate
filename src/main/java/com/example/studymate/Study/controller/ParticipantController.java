package com.example.studymate.Study.controller;


import com.example.studymate.Study.dto.JoinStudyRequest;
import com.example.studymate.Study.dto.JoinStudyResponse;
import com.example.studymate.Study.dto.MyStudyResponse;
import com.example.studymate.Study.service.ParticipantService;
import com.example.studymate.User.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ParticipantController {

    private final ParticipantService participantService;

    // 스터디 참여 요청
    @PostMapping("/api/studies/{studyId}/join")
    public ResponseEntity<JoinStudyResponse> joinStudy (@PathVariable Long studyId, @RequestBody JoinStudyRequest request) {
        JoinStudyResponse response = participantService.joinStudy(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 참여한 스터디 리스트 조회 기능
    @GetMapping("/api/users/me/studies")
    public ResponseEntity<MyStudyResponse> getMyStudyList(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(HttpStatus.OK).body(participantService.getMyStudyList(user));
    }

    // 참여한 스터디 중 진행중 스터디 리스트 조회 기능
    @GetMapping("/api/users/me/studies/ongoing")
    public ResponseEntity<MyStudyResponse> getMyStudyOngoingList(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(HttpStatus.OK).body(participantService.getMyStudyOngoingList(user));
    }
    
    // 참여한 스터디 중 완료 스터디 리스트 조회 기능
    @GetMapping("/api/users/me/studies/completed")
    public ResponseEntity<MyStudyResponse> getMyStudyCompletedList(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(HttpStatus.OK).body(participantService.getMyStudyCompletedList(user));
    }

}
