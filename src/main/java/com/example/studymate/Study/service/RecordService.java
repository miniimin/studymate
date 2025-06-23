package com.example.studymate.Study.service;

import com.example.studymate.Study.dto.*;
import com.example.studymate.Study.entity.StudyComment;
import com.example.studymate.Study.entity.StudyRecord;
import com.example.studymate.Study.repository.CommentRepository;
import com.example.studymate.Study.repository.ParticipantRepository;
import com.example.studymate.Study.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecordService {

    private final ParticipantRepository participantRepository;
    private final RecordRepository recordRepository;
    private final CommentRepository commentRepository;

    public AddRecordResponse createRecord(Long studyId,
                                          AddRecordRequest request) {
        Long userId = 1L;
        if (!participantRepository.existsByStudyGroupIdAndUserId(studyId, userId)) {
            throw new IllegalArgumentException("스터디 참여자가 아닙니다.");
        }
        StudyRecord record = recordRepository.save(request.toEntity());
        return new AddRecordResponse(record.getId());
    }

    public AddCommentResponse createComment(Long studyId,
                                            Long recordId,
                                            AddCommentRequest request) {
        Long userId = 1L;
        if (!participantRepository.existsByStudyGroupIdAndUserId(studyId, userId)) {
            throw new IllegalArgumentException("스터디 참여자가 아닙니다.");
        }
        if (!recordRepository.existsById(recordId)){
            throw new IllegalArgumentException("스터디 기록이 존재하지 않습니다.");
        }
        StudyComment comment = commentRepository.save(request.toEntity());
        return new AddCommentResponse(comment.getRecordId());
    }

    public RecordCommentResponse viewRecordAndComment(Long studyId,
                                                      Long recordId) {
        Long userId = 1L;
        if (!participantRepository.existsByStudyGroupIdAndUserId(studyId, userId)) {
            throw new IllegalArgumentException("스터디 참여자가 아닙니다.");
        }
        StudyRecord record = recordRepository.findById(recordId).orElseThrow(
                () -> new IllegalArgumentException("스터디 기록이 존재하지 않습니다.")
        );
        List<CommentResponse> commentList = commentRepository.findAllByRecordId(recordId)
                .stream()
                .map(CommentResponse::new)
                .toList();
        return new RecordCommentResponse(record, commentList);
    }


}
