package org.example.lms.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    @ManyToOne
    private Course course;



    public Lesson() {}

    public Lesson(String title, String content, Course course) {
        this.title = title;
        this.content = content;
        this.course = course;
    }


    public Long getId() { return id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

    public Course getCourse() { return course; }

    public void setCourse(Course course) { this.course = course; }



}
