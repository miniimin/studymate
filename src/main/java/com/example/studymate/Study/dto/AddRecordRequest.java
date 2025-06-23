package com.example.studymate.Study.dto;

import com.example.studymate.Study.entity.StudyRecord;
import lombok.Getter;

@Getter
public class AddRecordRequest {
    public Long studyGroupId;
    public Long authorId;
    public String authorName;
    public String title;
    public String content;

    public StudyRecord toEntity() {
        return StudyRecord.builder()
                .studyGroupId(studyGroupId)
                .authorId(authorId)
                .authorName(authorName)
                .title(title)
                .content(content)
                .build();
    }
}
