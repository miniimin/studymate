package com.example.studymate.Study.dto;

import java.util.List;

public class MyStudyResponse {
    List<StudySummary> onGoingStudies;
    List<StudySummary> completedStudies;

    public MyStudyResponse(List<StudySummary> onGoingStudies, List<StudySummary> completedStudies){
        this.onGoingStudies = onGoingStudies;
        this.completedStudies = completedStudies;
    }
}
