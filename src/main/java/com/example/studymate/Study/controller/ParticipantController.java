package com.example.studymate.Study.controller;


import com.example.studymate.Study.constant.ParticipantRole;
import com.example.studymate.Study.dto.*;
import com.example.studymate.Study.service.ParticipantService;
import com.example.studymate.User.entity.User;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ParticipantController {

    private final ParticipantService participantService;

    // 스터디 참가 요청
    @PostMapping("/api/studies/{studyId}/participants")
    public ResponseEntity<ParticipantResponse> joinStudy (@PathVariable Long studyId,
                                                        @AuthenticationPrincipal User user) {
        ParticipantResponse response = participantService.joinStudy(studyId, user);
        return ResponseEntity.ok().body(response);
    }

    // 스터디 참가자 전체 조회
    @GetMapping("/api/studies/{studyId}/participants")
    public ResponseEntity<List<ParticipantResponse>> getParticipants (@PathVariable Long studyId) {
        List<ParticipantResponse> response = participantService.getParticipants(studyId);
        return ResponseEntity.ok().body(response);
    }

    // 특정 유저가 스터디 참가자인지 확인
    @GetMapping("/api/studies/{studyId}/participants/me")
    public boolean isParticipant (@PathVariable Long studyId,
                                  @AuthenticationPrincipal User user) {
        return participantService.isParticipant(studyId, user);
    }

    // ------------------------------------------------------------------------------------------------

//    // 참여한 스터디 리스트 조회 기능
//    @GetMapping("/api/users/me/studies")
//    public ResponseEntity<MyStudyResponse> getMyStudyList(@AuthenticationPrincipal User user) {
//        return ResponseEntity.status(HttpStatus.OK).body(participantService.getMyStudyList(user));
//    }
//
    // 참여한 스터디 중 진행중 스터디 리스트 조회 기능
    @GetMapping("/api/users/me/studies/ongoing")
    public ResponseEntity<MyStudyPageResponse> getMyStudyOngoingList(@Positive int page,
                                                                     @Positive int size,
                                                                     @AuthenticationPrincipal User user) {
        return ResponseEntity.ok().body(participantService.getMyStudyOngoingList(page - 1, size, user));
    }

    // 참여한 스터디 중 완료 스터디 리스트 조회 기능
    @GetMapping("/api/users/me/studies/completed")
    public ResponseEntity<MyStudyPageResponse> getMyStudyCompletedList(@Positive int page,
                                                                       @Positive int size,
                                                                       @AuthenticationPrincipal User user) {
        return ResponseEntity.ok().body(participantService.getMyStudyCompletedList(page - 1, size, user));
    }

}
