package com.example.studymate.Study.repository;

import com.example.studymate.Study.dto.CommentResponse;
import com.example.studymate.Study.entity.StudyComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<StudyComment, Long> {

    List<CommentResponse> findAllByRecordId(Long recordId);

    @Query("""
            SELECT new com.example.studymate.Study.dto.CommentResponse(
            c.id, c.recordId, c.authorName, c.content, c.createdAt)
            FROM StudyComment c
            WHERE c.recordId IN :recordIds
            """)
    List<CommentResponse> findAllByRecordIdIn(@Param("recordIds") List<Long> recordIds);
}
