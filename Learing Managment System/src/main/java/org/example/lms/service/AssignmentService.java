package org.example.lms.service;

import org.example.lms.model.Assignment;
import org.example.lms.model.Course;
import org.example.lms.model.Lesson;
import org.example.lms.repository.AssignmentRepository;
import org.example.lms.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private CourseRepository courseRepository;


    public List<Assignment> getAllAssignments() {
        return assignmentRepository.findAll();
    }

    public List<Assignment> getAssignmentsByCourseId(Long courseId) {
        return assignmentRepository.findByCourseId(courseId);
    }

    public Assignment createAssignment(Long courseId, Assignment assignment) {
        Optional<Course> courseOpt = courseRepository.findById(courseId);
        if (courseOpt.isPresent()) {
            assignment.setCourse(courseOpt.get());
            return assignmentRepository.save(assignment);
        }
        return null;
    }

    public Assignment getAssignmentById(Long id) {
        return assignmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Lesson not found with id " + id));
    }

    public Assignment updateAssignment(Long id, Assignment updatedAssignment) {
        return assignmentRepository.findById(id).map(assignment -> {
            assignment.setTitle(updatedAssignment.getTitle());
            assignment.setDescription(updatedAssignment.getDescription());
            return assignmentRepository.save(assignment);
        }).orElse(null);
    }

    public void deleteAssignment(Long id) {
        assignmentRepository.deleteById(id);
    }
}
