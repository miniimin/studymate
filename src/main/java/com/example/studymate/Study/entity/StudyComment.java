package com.example.studymate.Study.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
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
    private LocalDateTime updatedAt;

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

    public void update(String content) {
        this.content = content;
        this.updatedAt = LocalDateTime.now();
    }
}
