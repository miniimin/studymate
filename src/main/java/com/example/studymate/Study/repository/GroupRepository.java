package com.example.studymate.Study.repository;

import com.example.studymate.Study.entity.StudyGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<StudyGroup, Long> {

    List<StudyGroup> findByRecruitDeadlineAfter(LocalDateTime now);

    @Query("SELECT s FROM StudyGroup s WHERE s.recruitDeadline > :now AND (s.title LIKE %:keyword% OR s.description LIKE %:keyword%)")
    List<StudyGroup> searchRecruitingStudy(@Param("now") LocalDateTime now, @Param("keyword") String query);

}
