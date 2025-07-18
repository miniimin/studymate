package com.example.studymate.Study.entity;

import com.example.studymate.Study.dto.UpdateRecordRequest;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class StudyRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long studyGroupId;
    private Long authorId;
    private String authorName;
    private String title;
    @Lob
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

    public void update(UpdateRecordRequest request) {
        this.title = request.getTitle();
        this.content = request.getContent();;
    }
}
