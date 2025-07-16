package com.example.studymate.Study.dto;

import com.example.studymate.Study.constant.ParticipantRole;
import com.example.studymate.Study.entity.StudyParticipant;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ParticipantResponse {
    private Long studyId;
    private Long userId;
    private ParticipantRole role;
    private LocalDateTime joinDate;

    public static ParticipantResponse from(StudyParticipant participant) {
        return new ParticipantResponse(participant.getStudyId(),
        participant.getUserId(),
        participant.getRole(),
        participant.getCreatedAt());
    }
}