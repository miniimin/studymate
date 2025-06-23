package com.example.studymate.Study.controller;

import com.example.studymate.Study.dto.*;
import com.example.studymate.Study.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class RecordController {

    private final RecordService recordService;

    // 기록 남기기 기능(스터디 진행 중일 때만 가능)
    @PostMapping("/api/study/{studyId}/record")
    public ResponseEntity<AddRecordResponse> createRecord(@PathVariable Long studyId,
                                                          @RequestBody AddRecordRequest request) {
        AddRecordResponse response = recordService.createRecord(studyId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 기록 덧글 달기 기능
    @PostMapping("/api/study/{studyId}/record/{recordId}/comment")
    public ResponseEntity<AddCommentResponse> createComment(@PathVariable Long studyId,
                                                            @PathVariable Long recordId,
                                                            @RequestBody AddCommentRequest request) {
        AddCommentResponse response = recordService.createComment(studyId, recordId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 기록 및 덧글 보기 기능
    @GetMapping("/api/study/{studyId}/record/{recordId}")
    public ResponseEntity<RecordCommentResponse> viewRecordComment(@PathVariable Long studyId,
                                                     @PathVariable Long recordId) {
        RecordCommentResponse response = recordService.viewRecordAndComment(studyId, recordId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
