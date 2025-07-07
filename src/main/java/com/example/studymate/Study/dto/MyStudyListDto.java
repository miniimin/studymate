package com.example.studymate.Study.dto;

import com.example.studymate.Study.constant.ParticipantRole;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MyStudyListDto {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer participantsMax;
    private LocalDateTime recruitDeadline;
    private ParticipantRole role;
}

