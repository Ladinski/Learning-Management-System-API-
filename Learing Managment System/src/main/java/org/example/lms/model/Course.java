package org.example.lms.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @ManyToOne
    @JsonIgnoreProperties({"coursesTeaching", "coursesEnrolled"})
    private User instructor;

    @ManyToMany
    @JsonIgnoreProperties({"coursesTeaching", "coursesEnrolled"})
    private List<User> students;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"content", "userId", "course"})
    private List<Lesson> lessons = new ArrayList<>();





    public Course() {}

    public Course(String title, String description, User instructor) {
        this.title = title;
        this.description = description;
        this.instructor = instructor;
    }


    public Long getId() { return id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public User getInstructor() { return instructor; }

    public void setInstructor(User instructor) { this.instructor = instructor; }

    public List<User> getStudents() { return students; }

    public void setStudents(List<User> students) { this.students = students; }

    public List<Lesson> getLessons() { return lessons; }


    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
        lesson.setCourse(this);
    }

    public void setLessons(List<Lesson> lessons) { this.lessons = lessons; }


}
