package com.example.studymate.Study.dto;

import com.example.studymate.Study.entity.StudyComment;
import lombok.Getter;
import lombok.Setter;

@Getter
public class AddCommentRequest {
    private String content;
}
