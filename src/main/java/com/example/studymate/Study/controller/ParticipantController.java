package com.example.studymate.Study.controller;


import com.example.studymate.Study.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ParticipantController {

    private final ParticipantService studyParticipantService;
}
