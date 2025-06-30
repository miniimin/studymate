package com.example.studymate.Study.repository;

import com.example.studymate.Study.entity.StudyGroup;
import com.example.studymate.Study.entity.StudyParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipantRepository extends JpaRepository<StudyParticipant, Long> {
    
    // 유저가 스터디 멤버인지 확인
    boolean existsByStudyGroupIdAndUserId(Long studyGroupId, Long userId);
    
    // 유저가 참여한 스터디 리스트 전부 확인
    List<StudyParticipant> findByUserId(Long userId);

    // 유저가 참여한 스터디 중 진행중 스터디 리스트 확인
    @Query("""
            SELECT g
            FROM StudyParticipant p
            JOIN StudyGroup g ON g.id = p.studyGroupId
            WHERE p.userId = :userId AND g.endDate > CURRENT_TIMESTAMP
            """)
    List<StudyGroup> findMyStudyOngoing(@Param("userId") Long userId);

    // 유저가 참여한 스터디 중 완료된 스터디 리스트 확인
    @Query("""
            SELECT g
            FROM StudyParticipant p
            JOIN StudyGroup g ON g.id = p.studyGroupId
            WHERE p.userId = :userId AND g.endDate < CURRENT_TIMESTAMP
            """)
    List<StudyGroup> findMyStudyCompleted(@Param("userId") Long userId);
}
