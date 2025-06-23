package com.example.studymate.Study.controller;


import com.example.studymate.Study.dto.JoinStudyRequest;
import com.example.studymate.Study.dto.JoinStudyResponse;
import com.example.studymate.Study.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ParticipantController {

    private final ParticipantService participantService;

    @PostMapping("/study/{id}/join")
    public ResponseEntity<JoinStudyResponse> joinStudy (@PathVariable Long id, @RequestBody JoinStudyRequest request) {
        JoinStudyResponse response = participantService.joinStudy(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
