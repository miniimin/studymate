package com.example.studymate.Study.dto;

import com.example.studymate.Study.entity.StudyGroup;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class StudySummary {
    private String title;
    private String description;
    private String creatorName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer participantsMax;

    public StudySummary(StudyGroup studyGroup) {
        this.title = studyGroup.getTitle();
        this.description = studyGroup.getDescription();
        this.creatorName = studyGroup.getCreatorName();
        this.startDate = studyGroup.getStartDate();
        this.endDate = studyGroup.getEndDate();
        this.participantsMax = studyGroup.getParticipantsMax();
    }
}
