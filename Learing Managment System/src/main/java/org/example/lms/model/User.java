package org.example.lms.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private boolean isInstructor;

    @OneToMany(mappedBy = "instructor")
    @JsonIgnoreProperties({"description", "students", "instructor", "lessons", "assignments"})
    private List<Course> coursesTeaching;


    @ManyToMany(mappedBy = "students")
    @JsonIgnoreProperties({"students", "instructor", "lessons", "description", "assignments"})
    private List<Course> coursesEnrolled;





    public User() {}

    public User(String name, String email, boolean isInstructor) {
        this.name = name;
        this.email = email;
        this.isInstructor = isInstructor;
    }

    //Getters and Setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public boolean isInstructor() {
        return isInstructor;
    }

    public void setInstructor(boolean isInstructor) {
        this.isInstructor = isInstructor;
    }

    public List<Course> getCoursesTeaching() {
        return coursesTeaching;
    }

    public void setCoursesTeaching(List<Course> coursesTeaching) {
        this.coursesTeaching = coursesTeaching;
    }

    public List<Course> getCoursesEnrolled() {
        return coursesEnrolled;
    }

    public void setCoursesEnrolled(List<Course> coursesEnrolled) {
        this.coursesEnrolled = coursesEnrolled;
    }

    public void addCourseEnrolled(Course course) {
        if (coursesEnrolled == null) {
            coursesEnrolled = new ArrayList<>();
        }
        if (!coursesEnrolled.contains(course)) {
            coursesEnrolled.add(course);
        }
    }


}
