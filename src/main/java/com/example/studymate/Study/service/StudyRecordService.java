package com.example.studymate.Study.service;

import com.example.studymate.Study.repository.StudyRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudyRecordService {

    private final StudyRecordRepository studyRecordRepository;

}
