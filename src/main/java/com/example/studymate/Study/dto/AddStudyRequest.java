package com.example.studymate.Study.dto;

import com.example.studymate.Study.entity.StudyGroup;
import lombok.Getter;

@Getter
public class AddStudyRequest {

    public String title;
    public String description;

    public StudyGroup toEntity() {
        return StudyGroup.builder()
                .title(title)
                .description(description)
                .build();
    }
}
