package com.example.studymate.Study.repository;

import com.example.studymate.Study.entity.StudyRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

    @Repository
    public interface RecordRepository extends JpaRepository<StudyRecord, Long> {
        List<StudyRecord> findAllByStudyGroupId(Long studyId);

    }
