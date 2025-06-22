package com.example.studymate.Study.repository;

import com.example.studymate.Study.entity.StudyComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<StudyComment, Long> {
}
