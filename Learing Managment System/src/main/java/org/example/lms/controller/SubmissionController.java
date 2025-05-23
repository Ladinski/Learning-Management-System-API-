package org.example.lms.controller;

import org.example.lms.model.Submission;
import org.example.lms.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/submissions")
public class SubmissionController {

    @Autowired
    private SubmissionService submissionService;

    @PostMapping("/assignment/{assignmentId}/student/{studentId}")
    public Submission createSubmission(
            @PathVariable Long assignmentId,
            @PathVariable Long studentId,
            @RequestBody Submission submission) {
        return submissionService.submitAssignment(assignmentId, studentId, submission);
    }

    @GetMapping
    public List<Submission> getAllSubmissions() {
        return submissionService.getAllSubmissions();
    }

    @GetMapping("/{id}")
    public Submission getSubmissionById(@PathVariable Long id) {
        return submissionService.getSubmissionById(id);
    }

    @PutMapping("/{id}")
    public Submission updateSubmission(@PathVariable Long id, @RequestBody Submission updatedSubmission) {
        return submissionService.updateSubmission(id, updatedSubmission);
    }

    @DeleteMapping("/{id}")
    public void deleteSubmission(@PathVariable Long id) {
        submissionService.deleteSubmission(id);
    }
}
