package com.example.studymate.Study.controller;


import com.example.studymate.Study.dto.JoinStudyRequest;
import com.example.studymate.Study.dto.JoinStudyResponse;
import com.example.studymate.Study.dto.MyStudyResponse;
import com.example.studymate.Study.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ParticipantController {

    private final ParticipantService participantService;

    // 스터디 참여 요청
    @PostMapping("/api/study/{id}/join")
    public ResponseEntity<JoinStudyResponse> joinStudy (@PathVariable Long id, @RequestBody JoinStudyRequest request) {
        JoinStudyResponse response = participantService.joinStudy(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 참여중 및 참여완료 스터디 리스트 조회 기능
    @GetMapping("/api/my-study")
    public ResponseEntity<MyStudyResponse> getMyStudyList() {
        return ResponseEntity.status(HttpStatus.OK).body(participantService.getMyStudyList());
    }

}
