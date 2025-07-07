package com.example.studymate.Study.dto;

import com.example.studymate.Study.entity.StudyComment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentResponse {

    private Long id;
    private Long recordId;
    private String authorName;
    private String content;
    private LocalDateTime createdAt;

    public static CommentResponse from(StudyComment comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getRecordId(),
                comment.getAuthorName(),
                comment.getContent(),
                comment.getCreatedAt()
        );
    }
}
