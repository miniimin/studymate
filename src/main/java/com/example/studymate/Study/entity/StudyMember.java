package com.example.studymate.Study.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "study_member")
public class StudyMember {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "study_member_id", updatable = false)
    private Long id;
    private Long userId;
    private Long studyGroupId;
    @CreatedDate
    private LocalDateTime createdAt;

}
