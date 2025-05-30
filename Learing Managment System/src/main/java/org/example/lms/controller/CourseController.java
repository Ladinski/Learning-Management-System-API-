package org.example.lms.controller;

import org.example.lms.model.Course;
import org.example.lms.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping
    public String createCourse(@RequestBody Course course) {
        return courseService.createCourse(course);
    }

    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public Course getCourse(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }

    @DeleteMapping("/{id}/user/{userId}")
    public String deleteCourse(@PathVariable Long id, @PathVariable Long userId) {
        return courseService.deleteCourse(id, userId);
    }

    @PutMapping("/{id}")
    public String updateCourse(@PathVariable Long id, @RequestBody Course course) {
        return courseService.updateCourse(id, course);
    }

    @PostMapping("/{courseId}/enroll/{studentId}")
    public String enrollStudent(@PathVariable Long courseId, @PathVariable Long studentId) {
        return courseService.enrollStudent(courseId, studentId);
    }

    @PatchMapping("/{courseId}/user/{instructorId}")
    public String updateInstructor(@PathVariable Long courseId, @PathVariable Long instructorId) {
        return courseService.updateInstructor(courseId, instructorId);
    }
}
