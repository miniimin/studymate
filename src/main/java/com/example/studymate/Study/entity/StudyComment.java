package com.example.studymate.Study.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudyComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long recordId;
    private Long authorId;
    private String authorName;
    private String content;
    @CreatedDate
    private LocalDateTime createdAt;

    @Builder
    public StudyComment(Long recordId,
                        Long authorId,
                        String authorName,
                        String content) {
        this.recordId = recordId;
        this.authorId = authorId;
        this.authorName = authorName;
        this.content = content;

    }
}
