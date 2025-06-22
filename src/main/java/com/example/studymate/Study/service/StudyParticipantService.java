package com.example.studymate.Study.service;

import com.example.studymate.Study.repository.StudyParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudyParticipantService {

    private final StudyParticipantRepository studyParticipantRepository;
}
