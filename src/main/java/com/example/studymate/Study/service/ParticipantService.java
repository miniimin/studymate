package com.example.studymate.Study.service;

import com.example.studymate.Study.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParticipantService {

    private final ParticipantRepository studyParticipantRepository;
}
