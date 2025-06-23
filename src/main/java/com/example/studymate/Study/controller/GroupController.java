package com.example.studymate.Study.controller;
import com.example.studymate.Study.dto.AddStudyRequest;
import com.example.studymate.Study.dto.AddStudyResponse;
import com.example.studymate.Study.dto.RecruitingStudyListResponse;
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

    @PostMapping("/study")
    public ResponseEntity<AddStudyResponse> createStudy(
            @Valid @RequestBody AddStudyRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(groupService.createStudy(request));
    }

    @GetMapping("/study/{id}")
    public ResponseEntity<StudyDetailResponse> getStudyDetail(@PathVariable Long studyId,
                                                              @RequestBody Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(groupService.getStudyDetail(studyId, userId));
    }

    @GetMapping("/recruiting-study")
    public ResponseEntity<List<RecruitingStudyListResponse>> getRecruitingStudyList() {
        List<RecruitingStudyListResponse> studyList = groupService.getRecruitingStudyList();
        return ResponseEntity.status(HttpStatus.OK).body(studyList);
    }


}
