package org.example.lms.service;

import org.example.lms.model.Course;
import org.example.lms.model.Lesson;
import org.example.lms.repository.CourseRepository;
import org.example.lms.repository.LessonRepository;
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

    public List<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    public List<Lesson> getLessonsByCourseId(Long courseId) {
        return lessonRepository.findByCourseId(courseId);
    }

    public Lesson createLesson(Long courseId, Lesson lesson) {
        Optional<Course> courseOpt = courseRepository.findById(courseId);
        if (courseOpt.isPresent()) {
            lesson.setCourse(courseOpt.get());
            return lessonRepository.save(lesson);
        }
        return null;
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

    public void deleteLesson(Long id) {
        lessonRepository.deleteById(id);
    }
}
