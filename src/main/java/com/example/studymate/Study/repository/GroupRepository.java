package com.example.studymate.Study.repository;

import com.example.studymate.Study.entity.StudyGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<StudyGroup, Long> {

}
