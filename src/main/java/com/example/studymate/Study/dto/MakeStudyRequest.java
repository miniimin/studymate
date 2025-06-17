package com.example.studymate.Study.dto;

import com.example.studymate.Study.entity.StudyGroup;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MakeStudyRequest {

    private String title;
    private String description;

    public StudyGroup toEntity() {
        return StudyGroup.builder()
                .title(title)
                .description(description)
                .build();
    }
}
