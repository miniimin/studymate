package com.example.studymate.Study.dto;

import com.example.studymate.Study.entity.StudyComment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddCommentRequest {

    private Long recordId;
    private Long authorId;
    private String authorName;
    private String content;

    public StudyComment toEntity() {
        return StudyComment.builder()
                .recordId(recordId)
                .authorId(authorId)
                .authorName(authorName)
                .content(content)
                .build();
    }
}
