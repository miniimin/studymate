package com.example.studymate.Study.dto;

import com.example.studymate.Study.entity.StudyParticipant;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
public class JoinStudyRequest {
    private Long userId;
    private Long studyGroupId;
}
