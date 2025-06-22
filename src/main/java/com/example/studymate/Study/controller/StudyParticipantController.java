package com.example.studymate.Study.controller;


import com.example.studymate.Study.service.StudyParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StudyParticipantController {

    private final StudyParticipantService studyParticipantService;
}
