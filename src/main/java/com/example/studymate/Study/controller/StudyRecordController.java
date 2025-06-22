package com.example.studymate.Study.controller;

import com.example.studymate.Study.service.StudyRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StudyRecordController {

    private final StudyRecordService studyRecordService;
}
