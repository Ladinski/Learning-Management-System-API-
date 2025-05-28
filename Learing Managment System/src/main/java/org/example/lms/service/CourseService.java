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

    //Creates a new course if the user is a valid instructor.
    public String createCourse(Course course) {
        Long instructorId = course.getInstructor().getId();

        User instructor = userRepository.findById(instructorId).orElse(null);
        if(instructor == null ){
            return "No user found by that id";
        }
        else if (!instructor.isInstructor()) {
            return "Only an instructor can create courses";
        }

        course.setInstructor(instructor);
        courseRepository.save(course);
        return "Course created successfully";
    }


    //Updates a course if the user is a valid instructor.
    public String updateCourse(Long id, Course updatedCourse) {
        return courseRepository.findById(id)
                .map(course -> {
                    User newInstructorInput = updatedCourse.getInstructor();
                    if (newInstructorInput == null || newInstructorInput.getId() == null) {
                        return "Instructor ID must be provided";
                    }

                    User newInstructor = userRepository.findById(newInstructorInput.getId())
                            .orElse(null);
                    if (newInstructor == null) {
                        return "Instructor not found";
                    }

                    if (!newInstructor.isInstructor()) {
                        return "Updated instructor must be an instructor";
                    }

                    course.setTitle(updatedCourse.getTitle());
                    course.setDescription(updatedCourse.getDescription());
                    course.setInstructor(newInstructor);
                    courseRepository.save(course);

                    return "Course updated successfully";
                })
                .orElse("Course not found");
    }

    //Deletes course by id only if the user deleting it is an instructor
    public String deleteCourse(Long id, Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if(user == null){
            return "No such user exists";
        } else if (!user.isInstructor()) {
            return "Students cannot delete courses!";
        } else if (!courseRepository.existsById(id)) {
            return "Course not found";
        }
        courseRepository.deleteById(id);
        return "Course deleted successfully";
    }


    //Enrolls student in course
    public String enrollStudent(Long courseId, Long studentId) {
        Course course = courseRepository.findById(courseId).orElse(null);
        User student = userRepository.findById(studentId).orElse(null);

        if (course == null || student == null) {
            return "Course or Student was not found";
        }

        if (student.isInstructor()) {
            return "User is an instructor, cannot enroll as student";
        }

        if (course.getStudents() == null) {
            course.setStudents(new ArrayList<>());
        }


        if (!course.getStudents().contains(student)) {
            course.getStudents().add(student);
        } else {
            return "Student already enrolled";
        }

        if (!student.getCoursesEnrolled().contains(course)) {
            student.addCourseEnrolled(course);
        }


        courseRepository.save(course);
        userRepository.save(student);

        return "Student enrolled successfully";
    }

    //Changes an instructor for a course
    public String updateInstructor(Long courseId, Long newInstructorId) {
        Course course = courseRepository.findById(courseId).orElse(null);
        User newInstructor = userRepository.findById(newInstructorId).orElse(null);

        if (course == null) {
            return "Course not found";
        }
        if (newInstructor == null) {
            return "Instructor not found";
        }
        if (!newInstructor.isInstructor()) {
            return "User is not an instructor";
        }

        course.setInstructor(newInstructor);
        courseRepository.save(course);

        return "Instructor updated successfully";
    }


}
