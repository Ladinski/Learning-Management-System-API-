package org.example.lms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    private String homework;

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonIgnoreProperties({"lessons", "description", "instructor", "assignments","students"})
    private Course course;


    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({"lesson","content"})
    private List<Assignment> assignments = new ArrayList<>();


    public Lesson() {}

    public Lesson(String title, String content, Course course, String homework) {
        this.title = title;
        this.content = content;
        this.course = course;
        this.homework = homework;
    }

    public void setHomework(String homework){
        this.homework = homework;
    }

    public String getHomework(){
        return homework;
    }
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }

}
