package com.example.studymate.Study.dto;

import com.example.studymate.Study.entity.StudyGroup;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class StudyResponse {
    private Long id;
    private String title;
    private String description;
    private String creatorName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer participantsMax;
    private LocalDateTime recruitDeadline;

    public static StudyResponse from(StudyGroup study) {
        return new StudyResponse(
        study.getId(),
        study.getTitle(),
        study.getDescription(),
        study.getCreatorName(),
        study.getStartDate(),
        study.getEndDate(),
        study.getParticipantsMax(),
        study.getRecruitDeadline()
        );
    }
}
