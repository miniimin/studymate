package com.example.studymate.Study.controller;

import com.example.studymate.Study.service.StudyCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StudyCommentController {

    private final StudyCommentService studyCommentService;

}
