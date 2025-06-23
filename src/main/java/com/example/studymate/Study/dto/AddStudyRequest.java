package com.example.studymate.Study.dto;

import com.example.studymate.Study.entity.StudyGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AddStudyRequest {

    @NotBlank(message = "스터디 제목은 필수입니다.") @Size(max = 30, message = "제목은 30자 이내여야 합니다.")
    private String title;

    @NotBlank(message = "스터디 제목은 필수입니다.") @Size(max = 30, message = "제목은 30자 이내여야 합니다.")
    private String description;

    private Long creatorId; // 스터디 생성자

    private String creatorName; // 스터디 생성자 닉네임

    @NotNull(message = "스터디 시작일은 필수입니다.")
    private LocalDateTime startDate; // 스터디 시작일

    @NotNull(message = "스터디 마감일은 필수입니다.")
    private LocalDateTime endDate; // 스터디 마감일

    @NotNull(message = "스터디 최대 참여 인원을 설정해주세요.")
    private Integer participantsMax; // 스터디 최대 참여 가능 인원

    private LocalDateTime recruitDeadline; // 스터디 모집 종료일

    public StudyGroup toEntity() {
        return StudyGroup.builder()
                .title(title)
                .description(description)
                .creatorId(creatorId)
                .creatorName(creatorName)
                .startDate(startDate)
                .endDate(endDate)
                .participantsMax(participantsMax)
                .recruitDeadline(recruitDeadline)
                .build();
    }
}
