package org.example.lms.repository;

import org.example.lms.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
    // Retrieves all lessons associated with a specific course ID
    List<Lesson> findByCourseId(Long courseId);
}
