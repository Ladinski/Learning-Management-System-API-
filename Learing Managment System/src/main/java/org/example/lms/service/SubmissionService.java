package org.example.lms.service;

import org.example.lms.model.Assignment;
import org.example.lms.model.Submission;
import org.example.lms.model.User;
import org.example.lms.repository.AssignmentRepository;
import org.example.lms.repository.SubmissionRepository;
import org.example.lms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubmissionService {

    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private UserRepository userRepository;


    public List<Submission> getAllSubmissions() {
        return submissionRepository.findAll();
    }

    public List<Submission> getSubmissionsByAssignmentId(Long assignmentId) {
        return submissionRepository.findByAssignmentId(assignmentId);
    }

    public Submission submitAssignment(Long assignmentId, Long studentId, Submission submission) {
        Optional<Assignment> assignmentOpt = assignmentRepository.findById(assignmentId);
        Optional<User> studentOpt = userRepository.findById(studentId);

        if (assignmentOpt.isPresent() && studentOpt.isPresent()) {
            submission.setAssignment(assignmentOpt.get());
            submission.setStudent(studentOpt.get());
            return submissionRepository.save(submission);
        }
        return null;
    }

    public Submission gradeSubmission(Long submissionId, int grade) {
        return submissionRepository.findById(submissionId).map(submission -> {
            submission.setGrade(grade);
            return submissionRepository.save(submission);
        }).orElse(null);
    }

    public Submission getSubmissionById(Long id) {
        return submissionRepository.findById(id).orElseThrow(() -> new RuntimeException("Lesson not found with id " + id));
    }


    public Submission updateSubmission(Long id, Submission updatedSubmission) {
        return submissionRepository.findById(id).map(submission -> {
            submission.setContent(updatedSubmission.getContent());
            submission.setGrade(updatedSubmission.getGrade());
            // add other fields if needed
            return submissionRepository.save(submission);
        }).orElse(null);
    }

    public void deleteSubmission(Long id) {
        submissionRepository.deleteById(id);
    }
}
