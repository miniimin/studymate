package com.example.studymate.Study.entity;

import com.example.studymate.Study.constant.ParticipantRole;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class StudyParticipant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long studyId;
    private Long userId;
    @CreatedDate
    private LocalDateTime createdAt;
    @Enumerated(EnumType.STRING)
    private ParticipantRole role;

    @Builder
    private StudyParticipant(Long studyId,
                             Long userId,
                             ParticipantRole role) {
        this.studyId = studyId;
        this.userId = userId;
        this.role = role;
    }
}
