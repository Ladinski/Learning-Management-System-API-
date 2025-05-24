package org.example.lms.repository;

import org.example.lms.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    List<Assignment> findByCourseId(Long courseId);

}