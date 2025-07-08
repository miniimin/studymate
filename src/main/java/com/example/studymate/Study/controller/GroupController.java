package com.example.studymate.Study.controller;
import com.example.studymate.Study.dto.*;
import com.example.studymate.Study.service.GroupService;
import com.example.studymate.User.entity.User;
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

    // 스터디 만들기 기능
    @PostMapping("/api/studies")
    public ResponseEntity<StudyResponse> addStudy(@RequestBody AddStudyRequest request,
                                                  @AuthenticationPrincipal User user) {
        StudyResponse savedStudy = groupService.addStudy(request, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudy);
    }

    // 스터디 전체 리스트 조회 기능
    @GetMapping("/api/studies")
    public ResponseEntity<List<StudyListResponse>> getStudyList() {
        List<StudyListResponse> studyList = groupService.getStudyList();
        return ResponseEntity.ok().body(studyList);
    }

    // 스터디 조회 기능
    @GetMapping("/api/studies/{studyId}")
    public ResponseEntity<StudyResponse> getStudy(@PathVariable Long studyId,
                                                  @AuthenticationPrincipal User user) {
        StudyResponse study = groupService.getStudy(studyId);
        return ResponseEntity.status(HttpStatus.OK).body(study);
    }

    //----------------------------------------------------------------------------

    // 모집중 스터디 리스트 조회 기능
//    @GetMapping("/api/studies/recruiting")
//    public ResponseEntity<List<StudyListResponse>> getRecruitingStudyList() {
//        List<StudyListResponse> studyList = groupService.getRecruitingStudyList();
//        return ResponseEntity.status(HttpStatus.OK).body(studyList);
//    }

    // 모집중 스터디 검색 기능
//    @GetMapping("/api/studies/recruiting/search")
//    public ResponseEntity<List<StudyListResponse>> getRecruitingSearchList(@RequestParam String query) {
//        List<StudyListResponse> studyList = groupService.getRecruitingSearchList(query);
//        return ResponseEntity.status(HttpStatus.OK).body(studyList);
//    }

}
