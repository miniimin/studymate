package com.example.studymate.Study.entity;

import com.example.studymate.Study.constant.ParticipantRole;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudyParticipant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long studyGroupId;
    private Long userId;
    @CreatedDate
    private LocalDateTime createdAt;
    @Enumerated(EnumType.STRING)
    private ParticipantRole role;

    public static StudyParticipant createMember(Long studyGroupId, Long userId) {
        return new StudyParticipant(studyGroupId, userId, ParticipantRole.MEMBER);
    }

    public static StudyParticipant createLeader(Long studyGroupId, Long userId) {
        return new StudyParticipant(studyGroupId, userId, ParticipantRole.LEADER);
    }

    private StudyParticipant(Long studyGroupId, Long userId, ParticipantRole role) {
        this.studyGroupId = studyGroupId;
        this.userId = userId;
        this.role = role;
    }

}
