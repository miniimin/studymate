package com.example.studymate.Study.service;

import com.example.studymate.Study.dto.JoinStudyRequest;
import com.example.studymate.Study.dto.JoinStudyResponse;
import com.example.studymate.Study.dto.MyStudyResponse;
import com.example.studymate.Study.dto.StudySummary;
import com.example.studymate.Study.entity.StudyGroup;
import com.example.studymate.Study.entity.StudyParticipant;
import com.example.studymate.Study.repository.GroupRepository;
import com.example.studymate.Study.repository.ParticipantRepository;
import com.example.studymate.User.entity.User;
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

    public MyStudyResponse getMyStudyList(User user) {
        Long userId = user.getId();

        List<StudySummary> onGoing = participantRepository.findMyStudyOngoing(userId)
                .stream()
                .map(StudySummary::new)
                .toList();
        List<StudySummary> completed = participantRepository.findMyStudyCompleted(userId)
                .stream()
                .map(StudySummary::new)
                .toList();

        return new MyStudyResponse(onGoing, completed);
    }

    public MyStudyResponse getMyStudyOngoingList(User user) {
        Long userId = user.getId();

        List<StudySummary> onGoing = participantRepository.findMyStudyOngoing(userId)
                .stream()
                .map(StudySummary::new)
                .toList();

        return new MyStudyResponse(onGoing, null);
    }

    public MyStudyResponse getMyStudyCompletedList(User user) {
        Long userId = user.getId();

        List<StudySummary> completed = participantRepository.findMyStudyCompleted(userId)
                .stream()
                .map(StudySummary::new)
                .toList();

        return new MyStudyResponse(null, completed);
    }

}
