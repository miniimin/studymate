package com.example.studymate.Study.service;

import com.example.studymate.Study.dto.*;
import com.example.studymate.Study.entity.StudyGroup;
import com.example.studymate.Study.entity.StudyParticipant;
import com.example.studymate.Study.repository.GroupRepository;
import com.example.studymate.Study.repository.ParticipantRepository;
import com.example.studymate.Study.repository.RecordRepository;
import com.example.studymate.User.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;

    public StudyResponse addStudy(AddStudyRequest request, User user) {
        StudyGroup study = StudyGroup.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .creatorId(user.getId())
                .creatorName(user.getNickname())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .recruitDeadline(request.getRecruitDeadline())
                .build();
        StudyGroup savedStudy = groupRepository.save(study);
        return StudyResponse.from(savedStudy);
    }

    public List<StudyListResponse> getStudyList() {
        return groupRepository.findAll()
                .stream()
                .map(StudyListResponse::from)
                .toList();
    }

    public StudyResponse getStudy(Long studyId) {
        StudyGroup study = groupRepository.findById(studyId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 스터디 id"));
        return StudyResponse.from(study);
    }

    public List<StudyListResponse> getRecruitingSearchList(String query, int page, int size) {
        return groupRepository.searchRecruitingStudy(LocalDateTime.now(), query)
                .stream()
                .map(StudyListResponse::from)
                .toList();
    }
}
