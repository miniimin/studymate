package com.example.studymate.Study.service;

import com.example.studymate.Study.constant.ParticipantRole;
import com.example.studymate.Study.dto.*;
import com.example.studymate.Study.entity.StudyParticipant;
import com.example.studymate.Study.repository.ParticipantRepository;
import com.example.studymate.User.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParticipantService {

    private final ParticipantRepository participantRepository;

    public ParticipantResponse joinStudy(Long studyId, User user) {
        return joinStudy(studyId, user, ParticipantRole.MEMBER);
    }

    public ParticipantResponse joinStudy(Long studyId, User user, ParticipantRole role) {
        Long userId = user.getId();

        if (participantRepository.existsByStudyIdAndUserId(studyId, userId)) {
            throw new IllegalArgumentException("이미 참여 중인 사용자");
        }

        StudyParticipant member = StudyParticipant
                .builder()
                .studyId(studyId)
                .userId(userId)
                .role(role)
                .build();

        participantRepository.save(member);
        return ParticipantResponse.from(member);
    }

    public List<ParticipantResponse> getParticipants (Long studyId) {
        return participantRepository.findAllByStudyId(studyId)
                .stream()
                .map(ParticipantResponse::from)
                .toList();
    }

    public boolean isParticipant(Long studyId, User user) {
        Long userId = user.getId();
         return participantRepository.existsByStudyIdAndUserId(studyId, userId);
    }

    public MyStudyPageResponse getMyStudyOngoingList(int page, int size, User user) {
        Long userId = user.getId();
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<MyStudyListDto> dto = participantRepository.findMyStudyOngoing(userId, pageable);
        return new MyStudyPageResponse(
                dto.getContent(),
                dto.getNumber(),
                dto.getTotalPages(),
                dto.getTotalElements(),
                dto.getSize(),
                dto.isLast()
        );
    }

    public MyStudyPageResponse getMyStudyCompletedList(int page, int size, User user) {
        Long userId = user.getId();
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<MyStudyListDto> dto = participantRepository.findMyStudyCompleted(userId, pageable);
        return new MyStudyPageResponse(
                dto.getContent(),
                dto.getNumber(),
                dto.getTotalPages(),
                dto.getTotalElements(),
                dto.getSize(),
                dto.isLast()
        );
    }
}