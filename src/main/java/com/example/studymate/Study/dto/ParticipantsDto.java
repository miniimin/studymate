package com.example.studymate.Study.dto;

//@Getter
//@AllArgsConstructor
//public class ParticipantResponse {
//    private Long studyId;
//    private Long userId;
//    private ParticipantRole role;
//    private LocalDateTime joinDate;
//
//    public static ParticipantResponse from(StudyParticipant participant) {
//        return new ParticipantResponse(participant.getStudyId(),
//        participant.getUserId(),
//        participant.getRole(),
//        participant.getCreatedAt());
//    }
//}

import com.example.studymate.Study.constant.ParticipantRole;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ParticipantsDto {
    private Long studyId;
    private Long userId;
    private ParticipantRole role;
    private LocalDateTime joinDate;
    private String nickname;
}