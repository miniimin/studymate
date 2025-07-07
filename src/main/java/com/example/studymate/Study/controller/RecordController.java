package com.example.studymate.Study.controller;

import com.example.studymate.Study.dto.*;
import com.example.studymate.Study.service.RecordService;
import com.example.studymate.User.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RecordController {

    private final RecordService recordService;

    // 기록 작성
    @PostMapping("/api/studies/{studyId}/records")
    public ResponseEntity<RecordResponse> addRecord(@PathVariable Long studyId,
                                                    @RequestBody AddRecordRequest request,
                                                    @AuthenticationPrincipal User user) {
        RecordResponse savedRecord = recordService.addRecord(studyId, request, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRecord);
    }

    // 기록 전체 리스트 조회
    @GetMapping("/api/studies/{studyId}/records")
    public ResponseEntity<List<RecordListResponse>> getRecordsList(@PathVariable Long studyId,
                                                               @AuthenticationPrincipal User user) {
        List<RecordListResponse> recordList = recordService.getRecordsList(studyId);
        return ResponseEntity.ok().body(recordList);
    }

    // 개별 기록 조회
    @GetMapping("/api/records/{recordId}")
    public ResponseEntity<RecordResponse> getRecord(@PathVariable Long recordId,
                                                    @AuthenticationPrincipal User user) {
        RecordResponse record = recordService.getRecord(recordId, user);
        return ResponseEntity.ok().body(record);
    }

    // 개별 기록 수정
    @PutMapping("/api/records/{recordId}")
    public ResponseEntity<RecordResponse> updateRecord(@PathVariable Long recordId,
                                                       @RequestBody UpdateRecordRequest request,
                                                       @AuthenticationPrincipal User user) {
        RecordResponse updatedRecord = recordService.updateRecord(recordId, request, user);
        return ResponseEntity.ok().body(updatedRecord);
    }

    // 개별 기록 삭제
    @DeleteMapping("/api/records/{recordId}")
    public ResponseEntity<Void> deleteRecord(@PathVariable Long recordId,
                                             @AuthenticationPrincipal User user) {
        recordService.deleteRecord(recordId, user);
        return ResponseEntity.ok().build();
    }

}
