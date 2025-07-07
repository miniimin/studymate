package com.example.studymate.Study.dto;

import com.example.studymate.Study.entity.StudyRecord;
import lombok.Getter;

@Getter
public class AddRecordRequest {
    public String title;
    public String content;
}
