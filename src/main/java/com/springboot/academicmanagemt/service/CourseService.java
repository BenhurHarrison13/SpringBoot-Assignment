package com.springboot.academicmanagemt.service;

import com.springboot.academicmanagemt.entity.Course;
import com.springboot.academicmanagemt.repository.CourseRepository;
import com.springboot.academicmanagemt.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course findCourse(Long courseId) {
        return courseRepository.findById(courseId).orElse(null);
    }

    public ResponseEntity<Course> updateCourse(Long id, Course updatedCourse) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Course existingCourse = optionalCourse.get();
        existingCourse.setName(updatedCourse.getName());
        existingCourse.setStudents(updatedCourse.getStudents());

        Course updatedCourseEntity = courseRepository.save(existingCourse);
        return ResponseEntity.ok(updatedCourseEntity);
    }

    public ResponseEntity<Void> deleteCourse(Long id) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Course course = optionalCourse.get();
        courseRepository.delete(course);

        return ResponseEntity.noContent().build();
    }
}
