package com.example.studymate.Study.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class RecordsWithCommentsResponse {

    private Long id;
    private String authorName;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    private List<CommentResponse> comments;

    public static RecordsWithCommentsResponse of(RecordResponse record, List<CommentResponse> comments) {
        return new RecordsWithCommentsResponse(
                record.getId(),
                record.getAuthorName(),
                record.getTitle(),
                record.getContent(),
                record.getCreatedAt(),
                comments
        );
    }
}
