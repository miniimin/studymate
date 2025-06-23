package com.example.studymate.Study.dto;

import com.example.studymate.Study.entity.StudyComment;

import java.time.LocalDateTime;

public class CommentResponse {
    private String authorName;
    private String content;
    private LocalDateTime createdAt;

    public CommentResponse(StudyComment comment) {
        this.authorName = comment.getAuthorName();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
    }
}
