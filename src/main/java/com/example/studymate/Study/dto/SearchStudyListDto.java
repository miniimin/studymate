    package com.example.studymate.Study.dto;

    import lombok.AllArgsConstructor;
    import lombok.Getter;

    import java.time.LocalDateTime;

    @Getter
    @AllArgsConstructor
    public class SearchStudyListDto {
        private Long id;
        private String title;
        private String description;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private Long participantsMax;
        private Long participantsNum;
        private LocalDateTime recruitDeadline;
        private LocalDateTime createdAt;

    }