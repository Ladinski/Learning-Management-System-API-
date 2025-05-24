package org.example.lms.controller;

import org.example.lms.model.Lesson;
import org.example.lms.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lessons")
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @PostMapping("/courses/{courseId}/lessons")
    public String createLesson(@PathVariable Long courseId, @RequestBody Lesson lesson) {
        return lessonService.createLesson(courseId, lesson);
    }


    @GetMapping
    public List<Lesson> getAllLessons() {
        return lessonService.getAllLessons();
    }

    @GetMapping("/{id}")
    public Lesson getLessonById(@PathVariable Long id) {
        return lessonService.getLessonById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteLesson(@PathVariable Long id) {
        lessonService.deleteLesson(id);
    }
}
