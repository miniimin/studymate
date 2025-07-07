package com.example.studymate.Study.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.web.ErrorResponse;

import java.time.LocalDateTime;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudyGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title; // 스터디 제목
    private String description; // 스터디 설명
    private Long creatorId; // 스터디 생성자
    private String creatorName; // 스터디 생성자 닉네임
    private LocalDateTime startDate; // 스터디 시작일
    private LocalDateTime endDate; // 스터디 마감일
    private Integer participantsMax; // 스터디 최대 참여 가능 인원
    private LocalDateTime recruitDeadline; // 스터디 모집 종료일
    @CreatedDate
    private LocalDateTime createdAt;
    private String studyStatus; // 스터디 시작전 / 진행중 / 종료 상태 확인

    @Builder
    public StudyGroup(String title,
                      String description,
                      Long creatorId,
                      String creatorName,
                      LocalDateTime startDate,
                      LocalDateTime endDate,
                      Integer participantsMax,
                      LocalDateTime recruitDeadline) {
        this.title = title;
        this.description = description;
        this.creatorId = creatorId;
        this.creatorName = creatorName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.participantsMax = participantsMax;
        this.recruitDeadline = recruitDeadline;
    }

}
