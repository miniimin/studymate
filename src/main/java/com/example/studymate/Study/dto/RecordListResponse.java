package com.example.studymate.Study.dto;

import com.example.studymate.Study.entity.StudyRecord;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RecordListResponse {
    public String title;
    public String content;
    public String authorName;
    public LocalDateTime createdAt;

    public RecordListResponse(StudyRecord record) {
        this.title = record.getTitle();
        this.content = record.getContent();
        this.authorName = record.getAuthorName();
        this.createdAt = record.getCreatedAt();
    }
}
