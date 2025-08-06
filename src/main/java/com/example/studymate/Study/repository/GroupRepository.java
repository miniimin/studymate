package com.example.studymate.Study.repository;

import com.example.studymate.Study.dto.SearchStudyListDto;
import com.example.studymate.Study.entity.StudyGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<StudyGroup, Long> {
    @Query("""
                SELECT s FROM StudyGroup s WHERE s.recruitDeadline > :now
                AND (s.title LIKE %:keyword% OR s.description LIKE %:keyword%)
            """)
    List<StudyGroup> findRecruitingStudies(@Param("now") LocalDateTime now, @Param("keyword") String query);

    @Query(""" 
                SELECT new com.example.studymate.Study.dto.SearchStudyListDto(
                    s.id, s.title, s.description, s.startDate, s.endDate,
                    s.participantsMax, COUNT(p.id), s.recruitDeadline, s.createdAt
                )
                FROM StudyGroup s
                LEFT JOIN StudyParticipant p ON p.studyId = s.id
                WHERE s.recruitDeadline >= :now
                    AND (s.title LIKE %:keyword% OR s.description LIKE %:keyword%)
                GROUP BY s.id, s.title, s.description, s.startDate, s.endDate, s.participantsMax, s.recruitDeadline, s.createdAt
                HAVING s.participantsMax > COUNT(p.id)
            """)
    Page<SearchStudyListDto> findRecruitingStudies(@Param("now") LocalDateTime now,
                                                   @Param("keyword") String query,
                                                   Pageable pageable);

    @Query("""
                SELECT new com.example.studymate.Study.dto.SearchStudyListDto(
                    s.id, s.title, s.description, s.startDate, s.endDate,
                    s.participantsMax, COUNT(p.id), s.recruitDeadline, s.createdAt
                )
                FROM StudyGroup s
                LEFT JOIN StudyParticipant p ON p.studyId = s.id
                WHERE s.endDate >= :now
                    AND (s.title LIKE %:keyword% OR s.description LIKE %:keyword%)
                GROUP BY s.id, s.title, s.description, s.startDate, s.endDate, s.participantsMax, s.recruitDeadline, s.createdAt
                HAVING (s.recruitDeadline < :now OR s.participantsMax <= COUNT(p.id))
            """)
    Page<SearchStudyListDto> findClosedStudies(@Param("now") LocalDateTime now,
                                               @Param("keyword") String query,
                                               Pageable pageable);

    @Query("""
                SELECT new com.example.studymate.Study.dto.SearchStudyListDto(
                    s.id, s.title, s.description, s.startDate, s.endDate,
                    s.participantsMax, COUNT(p.id), s.recruitDeadline, s.createdAt
                )
                FROM StudyGroup s
                LEFT JOIN StudyParticipant p ON p.studyId = s.id
                WHERE s.endDate < :now
                    AND (s.title LIKE %:keyword% OR s.description LIKE %:keyword%)
                GROUP BY s.id, s.title, s.description, s.startDate, s.endDate, s.participantsMax, s.recruitDeadline, s.createdAt
            """)
    Page<SearchStudyListDto> findCompletedStudies(@Param("now") LocalDateTime now,
                                                  @Param("keyword") String query,
                                                  Pageable pageable);

    @Query("""
                SELECT new com.example.studymate.Study.dto.SearchStudyListDto(
                    s.id, s.title, s.description, s.startDate, s.endDate,
                    s.participantsMax, COUNT(p.id), s.recruitDeadline, s.createdAt
                )
                FROM StudyGroup s
                LEFT JOIN StudyParticipant p ON p.studyId = s.id
                WHERE (s.title LIKE %:keyword% OR s.description LIKE %:keyword%)
                GROUP BY s.id, s.title, s.description, s.startDate, s.endDate, s.participantsMax, s.recruitDeadline, s.createdAt
            """)
    Page<SearchStudyListDto> findAllStudies(@Param("keyword") String query,
                                            Pageable pageable);

    @Query("SELECT s.participantsMax FROM StudyGroup s WHERE s.id = :id")
    Long findParticipantsMaxById(@Param("id") Long id);
}
