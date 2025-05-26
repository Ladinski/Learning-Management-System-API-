package org.example.lms.service;

import org.example.lms.model.Course;
import org.example.lms.model.Lesson;
import org.example.lms.model.User;
import org.example.lms.repository.CourseRepository;
import org.example.lms.repository.LessonRepository;
import org.example.lms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    public List<Lesson> getLessonsByCourseId(Long courseId) {
        return lessonRepository.findByCourseId(courseId);
    }

    public String createLesson(Long courseId, Lesson lesson) {
        Long instructorId = lesson.getUserId();

        User instructor = userRepository.findById(instructorId).orElse(null);
        if (instructor == null || !instructor.isInstructor()) {
            return "Either instructor doesn't exist or the user is a student";
        }

        Course course = courseRepository.findById(courseId).orElse(null);
        if (course == null) {
            return "Course not found";
        }

        if (!course.getInstructor().getId().equals(instructorId)) {
            return "User is not the instructor of this course";
        }

        lesson.setCourse(course);
        lessonRepository.save(lesson);
        return "Lesson created successfully";
    }




    public Lesson getLessonById(Long id) {
        return lessonRepository.findById(id).orElseThrow(() -> new RuntimeException("Lesson not found with id " + id));
    }

    public Lesson updateLesson(Long id, Lesson updatedLesson) {
        return lessonRepository.findById(id).map(lesson -> {
            lesson.setTitle(updatedLesson.getTitle());
            lesson.setContent(updatedLesson.getContent());
            return lessonRepository.save(lesson);
        }).orElse(null);
    }

    public String deleteLesson(Long id, Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if(user == null){
            return "No such user exists";
        } else if (!user.isInstructor()) {
            return "Students cannot delete Lessons!";
        } else if (!lessonRepository.existsById(id)) {
            return "Lesson not found";
        }
        lessonRepository.deleteById(id);
        return "Lesson deleted successfully";
    }
}
