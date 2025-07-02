package com.example.studymate.Study.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class MyStudyResponse {
    List<StudySummary> onGoingStudies;
    List<StudySummary> completedStudies;

    public MyStudyResponse(List<StudySummary> onGoingStudies, List<StudySummary> completedStudies){
        this.onGoingStudies = onGoingStudies;
        this.completedStudies = completedStudies;
    }
}
