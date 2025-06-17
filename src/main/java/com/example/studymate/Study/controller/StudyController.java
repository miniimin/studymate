package com.example.studymate.Study.controller;

import com.example.studymate.Study.dto.MakeStudyRequest;
import com.example.studymate.Study.entity.StudyGroup;
import com.example.studymate.Study.service.StudyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class StudyController {

    private StudyService studyService;

    @PostMapping
    public String makeStudy(@RequestBody MakeStudyRequest request) {
        return studyService.makeStudy(request);
    }
}
