package com.example.studymate.Study.service;

import com.example.studymate.Study.dto.AddStudyRequest;
import com.example.studymate.Study.dto.AddStudyResponse;
import com.example.studymate.Study.dto.StudyDetailResponse;
import com.example.studymate.Study.entity.StudyGroup;
import com.example.studymate.Study.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudyService {

    private final StudyRepository studyRepository;

    public AddStudyResponse createStudy(AddStudyRequest request) {
        Long id = studyRepository.save(request.toEntity()).getId();
        return new AddStudyResponse(id);
    }

    public StudyDetailResponse getStudyDetail(Long id) {
        StudyGroup study = studyRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 스터디가 존재하지 않습니다.")
        );
        return new StudyDetailResponse(study);
    }
}
