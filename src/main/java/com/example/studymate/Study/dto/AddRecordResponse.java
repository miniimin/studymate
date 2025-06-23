package com.example.studymate.Study.dto;

import lombok.Getter;

@Getter
public class AddRecordResponse {
    private Long id;
    private String message;

    public AddRecordResponse(Long id) {
        this.id = id;
    }
}
