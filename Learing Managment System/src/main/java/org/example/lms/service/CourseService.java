package org.example.lms.service;

import org.example.lms.model.Course;
import org.example.lms.model.User;
import org.example.lms.repository.CourseRepository;
import org.example.lms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id " + id));
    }


    public Course createCourse(Course course) {
        Long instructorId = course.getInstructor().getId();

        User instructor = userRepository.findById(instructorId).orElse(null);
        if (instructor == null || !instructor.isInstructor()) {
            throw new RuntimeException("Only an instructor can create courses");
        }

        course.setInstructor(instructor);
        return courseRepository.save(course);
    }

    public Course updateCourse(Long id, Course updatedCourse) {
        return courseRepository.findById(id)
                .map(course -> {
                    User newInstructorInput = updatedCourse.getInstructor();
                    if (newInstructorInput == null || newInstructorInput.getId() == null) {
                        throw new RuntimeException("Instructor ID must be provided");
                    }

                    // ✅ Fetch the full user entity from the DB
                    User newInstructor = userRepository.findById(newInstructorInput.getId())
                            .orElseThrow(() -> new RuntimeException("Instructor not found"));

                    if (!newInstructor.isInstructor()) {
                        throw new RuntimeException("Updated instructor must be an instructor");
                    }

                    course.setTitle(updatedCourse.getTitle());
                    course.setDescription(updatedCourse.getDescription());
                    course.setInstructor(newInstructor);
                    return courseRepository.save(course);
                })
                .orElse(null);
    }


    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    public String enrollStudent(Long courseId, Long studentId) {
        Course course = courseRepository.findById(courseId).orElse(null);
        User student = userRepository.findById(studentId).orElse(null);

        if (course == null || student == null) {
            return "{\"error\": \"Course or Student was not found\"}";
        }

        if (student.isInstructor()) {
            return "{\"error\": \"User is an instructor, cannot enroll as student\"}";
        }

        if (course.getStudents() == null) {
            course.setStudents(new ArrayList<>());
        }


        if (!course.getStudents().contains(student)) {
            course.getStudents().add(student);
        } else {
            return "{\"info\": \"Student already enrolled\"}";
        }

        if (!student.getCoursesEnrolled().contains(course)) {
            student.addCourseEnrolled(course);
        }

        // Save both entities to keep DB consistent
        courseRepository.save(course);
        userRepository.save(student);

        return "{\"success\": \"Student enrolled successfully\"}";
    }



}
