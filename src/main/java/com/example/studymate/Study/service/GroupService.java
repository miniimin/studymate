package com.example.studymate.Study.service;

import com.example.studymate.Study.dto.*;
import com.example.studymate.Study.entity.StudyGroup;
import com.example.studymate.Study.entity.StudyParticipant;
import com.example.studymate.Study.repository.GroupRepository;
import com.example.studymate.Study.repository.ParticipantRepository;
import com.example.studymate.Study.repository.RecordRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final ParticipantRepository participantRepository;
    private final RecordRepository recordRepository;

    @Transactional
    public AddStudyResponse createStudy(AddStudyRequest request) {
        StudyGroup study = groupRepository.save(request.toEntity());
        StudyParticipant leader = StudyParticipant.createLeader(study.getId(), study.getCreatorId());
        participantRepository.save(leader);
        return new AddStudyResponse(study.getId());
    }

    public StudyDetailResponse getStudyDetail(Long studyId, Long userId) {
        StudyGroup study = groupRepository.findById(studyId).orElseThrow(
                () -> new IllegalArgumentException("해당 스터디가 존재하지 않습니다.")
        );

        Boolean isParticipant = participantRepository.existByStudyGroupIdAndUserId(studyId, userId);

        List<RecordListResponse> recordList =
                recordRepository.findByStudyGroupId(studyId)
                        .stream()
                        .map(RecordListResponse::new)
                        .toList();
    return new StudyDetailResponse(study, isParticipant, recordList);
    }

    public List<StudySummary> getRecruitingStudyList() {
        return groupRepository.findByRecruitDeadlineAfter(LocalDateTime.now())
                .stream()
                .map(StudySummary::new)
                .toList();
    }

    public List<StudySummary> getRecruitingSearchList(String query) {
        return groupRepository.searchRecruitingStudy(LocalDateTime.now(), query)
                .stream()
                .map(StudySummary::new)
                .toList();
    }
}
