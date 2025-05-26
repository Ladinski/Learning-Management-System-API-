package org.example.lms.service;

import org.example.lms.model.Assignment;
import org.example.lms.model.Lesson;
import org.example.lms.model.User;
import org.example.lms.repository.AssignmentRepository;
import org.example.lms.repository.LessonRepository;
import org.example.lms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private UserRepository userRepository;

    public String submitAssignment(Long lessonId, Assignment assignment) {
        Lesson lesson = lessonRepository.findById(lessonId).orElse(null);
        if (lesson == null) {
            return "Lesson not found";
        }
        Long StudentId = assignment.getUserId();
        User student = userRepository.findById(StudentId).orElse(null);
        if (student == null || student.isInstructor()) {
            return "Only students can submit assignments";
        }

        assignment.setLesson(lesson);
        assignment.setUserId(student.getId());
        assignment.setStudentName(student.getName());
        assignment.setSubmittedAt(LocalDateTime.now());

        assignmentRepository.save(assignment);
        return "Assignment submitted";
    }

    public List<Assignment> getAssignmentsByLesson(Long lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId).orElse(null);
        return (lesson != null) ? lesson.getAssignments() : List.of();
    }

    public Assignment getAssignmentById(Long id) {
        return assignmentRepository.findById(id).orElse(null);
    }

    public String deleteAssignment(Long id, Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if(user == null){
            return "No such user exists";
        } else if (user.isInstructor()) {
            return "Instructors cannot delete assignment submissions!";
        } else if (!assignmentRepository.existsById(id)) {
            return "Assignment not found";
        }
        assignmentRepository.deleteById(id);
        return "Assignment deleted successfully";
    }
}
