package com.example.studymate.Study.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddStudyResponse {

    private Long id;
    private String message;

    public AddStudyResponse(Long id) {
        this.id = id;
    }
}
