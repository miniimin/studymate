package com.example.studymate.Study.service;

import com.example.studymate.Study.dto.JoinStudyRequest;
import com.example.studymate.Study.dto.JoinStudyResponse;
import com.example.studymate.Study.entity.StudyParticipant;
import com.example.studymate.Study.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParticipantService {

    private final ParticipantRepository participantRepository;

    public JoinStudyResponse joinStudy(JoinStudyRequest request) {
        if (participantRepository.existByStudyGroupIdAndUserId(request.getStudyGroupId(), request.getUserId())) {
            throw new IllegalArgumentException("이미 참여 중인 사용자입니다.");
        }
        StudyParticipant member = StudyParticipant.createLeader(request.getStudyGroupId(), request.getUserId());
        participantRepository.save(member);
        return new JoinStudyResponse();
    }
}
