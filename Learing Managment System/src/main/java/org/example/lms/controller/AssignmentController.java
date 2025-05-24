package org.example.lms.controller;

import org.example.lms.model.Assignment;
import org.example.lms.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assignments")
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    @PostMapping("/course/{courseId}")
    public Assignment createAssignment(@PathVariable Long courseId, @RequestBody Assignment assignment) {
        return assignmentService.createAssignment(courseId, assignment);
    }

    @GetMapping
    public List<Assignment> getAllAssignments() {
        return assignmentService.getAllAssignments();
    }

    @GetMapping("/{id}")
    public Assignment getAssignment(@PathVariable Long id) {
        return assignmentService.getAssignmentById(id);
    }

    @PutMapping("/{id}")
    public Assignment updateAssignment(@PathVariable Long id, @RequestBody Assignment assignment) {
        return assignmentService.updateAssignment(id, assignment);
    }

    @DeleteMapping("/{id}")
    public void deleteAssignment(@PathVariable Long id) {
        assignmentService.deleteAssignment(id);
    }
}