package com.example.studymate.Study.controller;
import com.example.studymate.Study.dto.AddStudyRequest;
import com.example.studymate.Study.dto.AddStudyResponse;
import com.example.studymate.Study.dto.StudySummary;
import com.example.studymate.Study.dto.StudyDetailResponse;
import com.example.studymate.Study.service.GroupService;
import com.example.studymate.User.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;
    private final ObjectMapper objectMapper;

    // 스터디 생성 기능
    @PostMapping("/api/studies")
    public ResponseEntity<AddStudyResponse> createStudy(@AuthenticationPrincipal User user ,
                                                        @RequestBody AddStudyRequest request) throws JsonProcessingException {
        System.out.println(objectMapper.writeValueAsString(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(groupService.createStudy(request, user));
    }

    // 스터디 상세 조회 기능
    @GetMapping("/api/studies/{studyId}")
    public ResponseEntity<StudyDetailResponse> getStudyDetail(@PathVariable Long studyId,
                                                              @AuthenticationPrincipal User user) {
        return ResponseEntity.status(HttpStatus.OK).body(groupService.getStudyDetail(studyId, user.getId()));
    }

    // 모집중 스터디 리스트 조회 기능
    @GetMapping("/api/studies/recruiting")
    public ResponseEntity<List<StudySummary>> getRecruitingStudyList() {
        List<StudySummary> studyList = groupService.getRecruitingStudyList();
        return ResponseEntity.status(HttpStatus.OK).body(studyList);
    }

    // 모집중 스터디 검색 기능
    @GetMapping("/api/studies/recruiting/search")
    public ResponseEntity<List<StudySummary>> getRecruitingSearchList(@RequestParam String query) {
        List<StudySummary> studyList = groupService.getRecruitingSearchList(query);
        return ResponseEntity.status(HttpStatus.OK).body(studyList);
    }

}
