package com.example.studymate.Study.controller;
import com.example.studymate.Study.dto.AddStudyRequest;
import com.example.studymate.Study.dto.AddStudyResponse;
import com.example.studymate.Study.dto.StudyDetailResponse;
import com.example.studymate.Study.service.StudyGroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class StudyGroupController {

    private final StudyGroupService studyService;

    @PostMapping("/study")
    public ResponseEntity<AddStudyResponse> createStudy(
            @Valid @RequestBody AddStudyRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studyService.createStudy(request));
    }

    @GetMapping("/study/{id}")
    public ResponseEntity<StudyDetailResponse> getStudyDetail(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(studyService.getStudyDetail(id));
    }

}
