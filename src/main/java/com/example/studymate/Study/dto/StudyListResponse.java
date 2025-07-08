package com.example.studymate.Study.dto;

import com.example.studymate.Study.entity.StudyGroup;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class StudyListResponse {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long participantsMax;
    private LocalDateTime recruitDeadline;

    public static StudyListResponse from(StudyGroup study) {
        return new StudyListResponse(
                study.getId(),
                study.getTitle(),
                study.getDescription(),
                study.getStartDate(),
                study.getEndDate(),
                study.getParticipantsMax(),
                study.getRecruitDeadline()
        );
    }
}

