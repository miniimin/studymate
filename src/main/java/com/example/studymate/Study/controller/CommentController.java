package com.example.studymate.Study.controller;

import com.example.studymate.Study.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService studyCommentService;

}
