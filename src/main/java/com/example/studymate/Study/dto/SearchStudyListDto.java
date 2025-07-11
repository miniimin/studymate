    package com.example.studymate.Study.dto;

    import lombok.AllArgsConstructor;
    import lombok.Getter;

    import java.time.LocalDateTime;

//    @Getter
//    @AllArgsConstructor
//    public class SearchStudyListDto {
//        private Long id;
//        private String title;
//        private String description;
//        private LocalDateTime startDate;
//        private LocalDateTime endDate;
//        private Long participantsMax;
//        private Long participantsNum;
//        private LocalDateTime recruitDeadline;
//        private LocalDateTime createdAt;
//
//    }

    @Getter
    public class SearchStudyListDto {
        private Long id;
        private String title;
        private String description;
        private String startDate;
        private String endDate;
        private Long participantsMax;
        private Long participantsNum;
        private String recruitDeadline;
        private String createdAt;

        public SearchStudyListDto(Long id, String title, String description,
                                  LocalDateTime startDate, LocalDateTime endDate,
                                  Long participantsMax, Long participantsNum,
                                  LocalDateTime recruitDeadline, LocalDateTime createdAt) {
            this.id = id;
            this.title = title;
            this.description = description;
            this.startDate = format(startDate);
            this.endDate = format(endDate);
            this.participantsMax = participantsMax;
            this.participantsNum = participantsNum;
            this.recruitDeadline = format(recruitDeadline);
            this.createdAt = format(createdAt);
        }

        private String format(LocalDateTime time) {
            return time != null ? time.toLocalDate().toString() : null;
        }
    }