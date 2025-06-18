package com.example.studymate.Study.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "study_member")
public class StudyMember {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long studyGroupId;
    @CreatedDate
    private LocalDateTime createdAt;

}
