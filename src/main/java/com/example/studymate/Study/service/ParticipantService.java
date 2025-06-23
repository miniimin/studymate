package com.example.studymate.Study.service;

import com.example.studymate.Study.dto.JoinStudyRequest;
import com.example.studymate.Study.dto.JoinStudyResponse;
import com.example.studymate.Study.dto.MyStudyResponse;
import com.example.studymate.Study.dto.StudySummary;
import com.example.studymate.Study.entity.StudyGroup;
import com.example.studymate.Study.entity.StudyParticipant;
import com.example.studymate.Study.repository.GroupRepository;
import com.example.studymate.Study.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ParticipantService {

    private final ParticipantRepository participantRepository;
    private final GroupRepository groupRepository;

    public JoinStudyResponse joinStudy(JoinStudyRequest request) {
        if (participantRepository.existsByStudyGroupIdAndUserId(request.getStudyGroupId(), request.getUserId())) {
            throw new IllegalArgumentException("이미 참여 중인 사용자입니다.");
        }
        StudyParticipant member = StudyParticipant.createMember(request.getStudyGroupId(), request.getUserId());
        participantRepository.save(member);
        return new JoinStudyResponse();
    }

    public MyStudyResponse getMyStudyList() {
        Long userId = 1L;

        List<Long> participantIds = participantRepository.findByUserId(userId)
                .stream()
                .map(StudyParticipant::getStudyGroupId)
                .toList();

        List<StudyGroup> myStudies = groupRepository.findAllById(participantIds);

        LocalDateTime now = LocalDateTime.now();

        List<StudySummary> onGoingStudies = myStudies
                .stream()
                .filter(study -> study.getEndDate().isAfter(now))
                .map(StudySummary::new)
                .toList();
        List<StudySummary> completedStudies = myStudies
                .stream()
                .filter(study -> study.getEndDate().isBefore(now))
                .map(StudySummary::new)
                .toList();

        return new MyStudyResponse(onGoingStudies, completedStudies);
    }
}
