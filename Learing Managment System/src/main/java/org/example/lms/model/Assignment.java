package org.example.lms.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private LocalDateTime submittedAt;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    @JsonIgnoreProperties({"content", "userId", "course"})
    private Lesson lesson;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonIgnoreProperties({"isInstructor", "coursesTeaching", "coursesEnrolled"})
    private User student;

    public Assignment() {
        this.submittedAt = LocalDateTime.now();
    }

    // Getters and setters
    public Long getId() { return id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public LocalDateTime getSubmittedAt() { return submittedAt; }
    public void setSubmittedAt(LocalDateTime submittedAt) { this.submittedAt = submittedAt; }

    public Lesson getLesson() { return lesson; }
    public void setLesson(Lesson lesson) { this.lesson = lesson; }

    public User getStudent() { return student; }
    public void setStudent(User student) { this.student = student; }
}
