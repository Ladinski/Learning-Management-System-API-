package org.example.lms.controller;

import org.example.lms.model.Assignment;
import org.example.lms.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lessons/{lessonId}/assignments")
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    @PostMapping
    public String submitAssignment(@PathVariable Long lessonId, @RequestBody Assignment assignment) {
        return assignmentService.submitAssignment(lessonId, assignment);
    }

    @GetMapping
    public List<Assignment> getAssignmentsByLesson(@PathVariable Long lessonId) {
        return assignmentService.getAssignmentsByLesson(lessonId);
    }

    @GetMapping("/{assignmentId}")
    public Assignment getAssignmentById(@PathVariable Long assignmentId) {
        return assignmentService.getAssignmentById(assignmentId);
    }

    @DeleteMapping("/{assignmentId}")
    public void deleteAssignment(@PathVariable Long assignmentId) {
        assignmentService.deleteAssignment(assignmentId);
    }
}
