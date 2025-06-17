package com.example.studymate.Study.service;

import com.example.studymate.Study.dto.MakeStudyRequest;
import com.example.studymate.Study.entity.StudyGroup;
import com.example.studymate.Study.repository.StudyRepository;
import org.springframework.stereotype.Service;

@Service
public class StudyService {

    private StudyRepository studyRepository;

    public String makeStudy(MakeStudyRequest request) {
        studyRepository.save(request.toEntity());
        return "Success";
    }
}
