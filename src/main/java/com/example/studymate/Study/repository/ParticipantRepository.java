package com.example.studymate.Study.repository;

import com.example.studymate.Study.dto.MyStudyListDto;
import com.example.studymate.Study.entity.StudyParticipant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipantRepository extends JpaRepository<StudyParticipant, Long> {

    // 스터디 참여 멤버 전부 확인
    List<StudyParticipant> findAllByStudyId(Long studyId);

    // 유저가 스터디 멤버인지 확인
    boolean existsByStudyIdAndUserId(Long studyId, Long userId);

    // 유저가 참여한 스터디 리스트 전부 확인
    List<StudyParticipant> findByUserId(Long userId);

    // 유저가 참여한 스터디 중 진행중 스터디 리스트 확인
    @Query("""
            SELECT new com.example.studymate.Study.dto.MyStudyListDto(
                s.id, s.title, s.description, s.startDate, s.endDate,
                s.participantsMax, s.recruitDeadline, p.role
            )
            FROM StudyParticipant p
            JOIN StudyGroup s ON s.id = p.studyId
            WHERE p.userId = :userId AND s.endDate > CURRENT_TIMESTAMP
            """)
    Page<MyStudyListDto> findMyStudyOngoing(@Param("userId") Long userId,
                                            Pageable pageable);

    // 유저가 참여한 스터디 중 완료된 스터디 리스트 확인
    @Query("""
            SELECT new com.example.studymate.Study.dto.MyStudyListDto(
                s.id, s.title, s.description, s.startDate, s.endDate,
                s.participantsMax, s.recruitDeadline, p.role
            )
            FROM StudyParticipant p
            JOIN StudyGroup s ON s.id = p.studyId
            WHERE p.userId = :userId AND s.endDate < CURRENT_TIMESTAMP
            """)
    Page<MyStudyListDto> findMyStudyCompleted(@Param("userId") Long userId,
                                              Pageable pageable);
}
