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
public class StudyRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long studyGroupId;
    private Long authorId;
    private String authorName;
    private String title;
    private String content;
    @CreatedDate
    private LocalDateTime createdAt;

    @Builder
    public StudyRecord(Long studyGroupId,
                       Long authorId,
                       String authorName,
                       String title,
                       String content) {
        this.studyGroupId = studyGroupId;
        this.authorId = authorId;
        this.authorName = authorName;
        this.title = title;
        this.content = content;
    }
}
