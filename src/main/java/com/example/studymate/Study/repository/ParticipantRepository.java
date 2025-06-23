package com.example.studymate.Study.repository;

import com.example.studymate.Study.entity.StudyParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends JpaRepository<StudyParticipant, Long> {
    boolean existByStudyGroupIdAndUserId(Long studyGroupId, Long userId);
}
