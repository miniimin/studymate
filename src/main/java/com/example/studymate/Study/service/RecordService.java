package com.example.studymate.Study.service;

import com.example.studymate.Study.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository studyRecordRepository;

}
