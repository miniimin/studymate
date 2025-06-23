package com.example.studymate.Study.controller;
import com.example.studymate.Study.dto.AddStudyRequest;
import com.example.studymate.Study.dto.AddStudyResponse;
import com.example.studymate.Study.dto.StudySummary;
import com.example.studymate.Study.dto.StudyDetailResponse;
import com.example.studymate.Study.service.GroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    // 스터디 생성 기능
    @PostMapping("/api/study")
    public ResponseEntity<AddStudyResponse> createStudy(
            @Valid @RequestBody AddStudyRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(groupService.createStudy(request));
    }

    // 스터디 상세 조회 기능
    @GetMapping("/api/study/{id}")
    public ResponseEntity<StudyDetailResponse> getStudyDetail(@PathVariable Long studyId,
                                                              @RequestBody Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(groupService.getStudyDetail(studyId, userId));
    }

    // 모집중 스터디 리스트 조회 기능
    @GetMapping("/api/recruiting-study")
    public ResponseEntity<List<StudySummary>> getRecruitingStudyList() {
        List<StudySummary> studyList = groupService.getRecruitingStudyList();
        return ResponseEntity.status(HttpStatus.OK).body(studyList);
    }

    // 모집중 스터디 검색 기능
    @GetMapping("/api/recruiting-study/{query}")
    public ResponseEntity<List<StudySummary>> getRecruitingSearchList(@PathVariable String query) {
        List<StudySummary> studyList = groupService.getRecruitingSearchList(query);
        return ResponseEntity.status(HttpStatus.OK).body(studyList);
    }

}
