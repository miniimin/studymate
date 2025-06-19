package com.example.studymate.Study.dto;

import com.example.studymate.Study.entity.StudyGroup;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class StudyDetailResponse {

    public String title;
    public String description;
    public Long creatorId; // 스터디 생성자
    public String creatorName; // 스터디 생성자 닉네임
    public LocalDateTime startDate; // 스터디 시작일
    public LocalDateTime endDate; // 스터디 마감일
    public Integer participantsMax; // 스터디 최대 참여 가능 인원
    public LocalDateTime recruitDeadline; // 스터디 모집 종료일

    public StudyDetailResponse(StudyGroup study) {
        this.title = study.getTitle();
        this.description = study.getDescription();
        this.creatorId = study.getCreatorId();
        this.creatorName = study.getCreatorName();
        this.startDate = study.getStartDate();
        this.endDate = study.getEndDate();
        this.participantsMax = study.getParticipantsMax();
        this.recruitDeadline = study.getRecruitDeadline();
    }
}
