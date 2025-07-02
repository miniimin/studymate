package com.example.studymate.Study.controller;

import com.example.studymate.Study.dto.*;
import com.example.studymate.Study.service.RecordService;
import com.example.studymate.User.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class RecordController {

    private final RecordService recordService;

    // 기록 남기기 기능(스터디 진행 중일 때만 가능)
    @PostMapping("/api/studies/{studyId}/records")
    public ResponseEntity<AddRecordResponse> createRecord(@PathVariable Long studyId,
                                                          @RequestBody AddRecordRequest request,
                                                          @AuthenticationPrincipal User user) {
        AddRecordResponse response = recordService.createRecord(studyId, request, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 기록 덧글 달기 기능
    @PostMapping("/api/studies/{studyId}/records/{recordId}/comments")
    public ResponseEntity<AddCommentResponse> createComment(@PathVariable Long studyId,
                                                            @PathVariable Long recordId,
                                                            @RequestBody AddCommentRequest request,
                                                            @AuthenticationPrincipal User user) {
        AddCommentResponse response = recordService.createComment(studyId, recordId, request, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 기록 및 덧글 보기 기능
    @GetMapping("/api/studies/{studyId}/records/{recordId}")
    public ResponseEntity<RecordCommentResponse> viewRecordComment(@PathVariable Long studyId,
                                                                   @PathVariable Long recordId,
                                                                   @AuthenticationPrincipal User user) {
        RecordCommentResponse response = recordService.viewRecordAndComment(studyId, recordId, user);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
