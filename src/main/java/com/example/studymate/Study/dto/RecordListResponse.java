package com.example.studymate.Study.dto;

import com.example.studymate.Study.entity.StudyRecord;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class RecordListResponse {
    private Long id;
    private Long studyGroupId;
    private String authorName;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    public static RecordListResponse from(StudyRecord record) {
        return new RecordListResponse(
                record.getId(),
                record.getStudyGroupId(),
                record.getAuthorName(),
                record.getTitle(),
                record.getContent(),
                record.getCreatedAt()
        );
    }
}
