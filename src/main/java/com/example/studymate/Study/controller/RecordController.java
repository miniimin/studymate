package com.example.studymate.Study.controller;

import com.example.studymate.Study.dto.*;
import com.example.studymate.Study.service.RecordService;
import com.example.studymate.User.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Map<String, Object>> getRecordsList(@PathVariable Long studyId,
                                                                   @RequestParam int page,
                                                                   @RequestParam int size,
                                                                   @AuthenticationPrincipal User user) {
        Page<RecordListResponse> recordPage = recordService.getRecordsList(page - 1, size, studyId);

        Map<String, Object> response = new HashMap<>();
        response.put("recordList", recordPage.getContent());
        response.put("currentPage", recordPage.getNumber());
        response.put("totalPages", recordPage.getTotalPages());

        return ResponseEntity.ok().body(response);
    }

    // 개별 기록 조회
    @GetMapping("/api/records/{recordId}")
    public ResponseEntity<RecordResponse> getRecord(@PathVariable Long recordId,
                                                    @AuthenticationPrincipal User user) {
        RecordResponse record = recordService.getRecord(recordId);
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
