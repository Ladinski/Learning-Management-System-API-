package org.example.lms.model;

import jakarta.persistence.*;

@Entity
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User student;

    @ManyToOne
    private Assignment assignment;

    private String content;
    private Integer grade;


    public Submission() {}

    public Submission(User student, Assignment assignment, String content, Integer grade) {
        this.student = student;
        this.assignment = assignment;
        this.content = content;
        this.grade = grade;
    }


    public Long getId() { return id; }

    public User getStudent() { return student; }

    public void setStudent(User student) { this.student = student; }

    public Assignment getAssignment() { return assignment; }

    public void setAssignment(Assignment assignment) { this.assignment = assignment; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

    public Integer getGrade() { return grade; }

    public void setGrade(Integer grade) { this.grade = grade; }
}
