package com.example.studymate.Study.service;

import com.example.studymate.Study.dto.*;
import com.example.studymate.Study.entity.StudyRecord;
import com.example.studymate.Study.repository.RecordRepository;
import com.example.studymate.User.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository recordRepository;

    public RecordResponse addRecord(Long studyId,
                                    AddRecordRequest request,
                                    User user) {
        StudyRecord studyRecord = StudyRecord.builder()
                .studyGroupId(studyId)
                .authorId(user.getId())
                .authorName(user.getNickname())
                .title(request.getTitle())
                .content(request.getContent())
                .build();
        StudyRecord savedRecord = recordRepository.save(studyRecord);
        return RecordResponse.from(savedRecord);
    }

    public List<RecordListResponse> getRecordsList(Long studyId) {
        return recordRepository.findRecordListByStudyGroupId(studyId);
    }

    public List<RecordResponse> getRecords(Long studyId) {
        return recordRepository.findByStudyGroupId(studyId);
    }

    public RecordResponse getRecord(Long recordId,
                                    User user) {
        StudyRecord record = recordRepository.findById(recordId)
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 기록이 없습니다."));

        return RecordResponse.from(record);
    }

    public RecordResponse updateRecord(Long recordId,
                                       UpdateRecordRequest request,
                                       User user) {
        StudyRecord record = recordRepository.findById(recordId)
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 기록이 없습니다."));

        record.update(request);
        return RecordResponse.from(record);
    }

    public void deleteRecord(Long recordId,
                             User user) {
        StudyRecord record = recordRepository.findById(recordId)
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 기록이 없습니다."));

        recordRepository.deleteById(recordId);
    }

}
