package com.example.studymate.Study.service;

import com.example.studymate.Study.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository studyCommentRepository;

}
