package com.example.studymate.Study.repository;

import com.example.studymate.Study.dto.RecordListResponse;
import com.example.studymate.Study.dto.RecordResponse;
import com.example.studymate.Study.entity.StudyRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

    @Repository
    public interface RecordRepository extends JpaRepository<StudyRecord, Long> {
        List<StudyRecord> findAllByStudyGroupId(Long studyId);

        @Query("""
                SELECT new com.example.studymate.Study.dto.RecordListResponse(
                r.id, r.studyGroupId, r.authorName, r.title, r.createdAt)
                FROM StudyRecord r
                WHERE r.studyGroupId = :studyId
                """)
        List<RecordListResponse> findRecordListByStudyGroupId(@Param("studyId") Long studyId);

        List<RecordResponse> findByStudyGroupId(Long studyId);
    }
