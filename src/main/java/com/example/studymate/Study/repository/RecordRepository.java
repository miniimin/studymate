package com.example.studymate.Study.repository;

import com.example.studymate.Study.entity.StudyRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepository extends JpaRepository<StudyRecord, Long> {
}
