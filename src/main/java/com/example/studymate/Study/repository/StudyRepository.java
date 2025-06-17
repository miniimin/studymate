package com.example.studymate.Study.repository;

import com.example.studymate.Study.entity.StudyGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyRepository extends JpaRepository<StudyGroup, Long> {

}
