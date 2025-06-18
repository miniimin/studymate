package com.example.studymate.Study.service;

import com.example.studymate.Study.dto.MakeStudyRequest;
import com.example.studymate.Study.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudyService {

    @Autowired
    final StudyRepository studyRepository;

    public String makeStudy(MakeStudyRequest request) {
        studyRepository.save(request.toEntity());
        return "SSuccess";
    }
}
