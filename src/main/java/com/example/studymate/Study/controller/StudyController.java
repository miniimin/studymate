package com.example.studymate.Study.controller;
import com.example.studymate.Study.dto.MakeStudyRequest;
import com.example.studymate.Study.service.StudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudyController {

    @Autowired
    private StudyService studyService;

    @PostMapping
    public String makeStudy(@RequestBody MakeStudyRequest request) {
        return studyService.makeStudy(request);
    }
}
