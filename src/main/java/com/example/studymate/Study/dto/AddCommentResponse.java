package com.example.studymate.Study.dto;

import lombok.Getter;

@Getter
public class AddCommentResponse {
    private Long recordId;
    private String message;
    public AddCommentResponse(Long recordId) {
        this.recordId = recordId;
    }
}
