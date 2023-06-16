package com.springboot.academicmanagemt.controller;

import com.springboot.academicmanagemt.entity.Course;
import com.springboot.academicmanagemt.rest.CourseController;
import com.springboot.academicmanagemt.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CourseControllerTest {

    @Mock
    private CourseService courseService;

    @InjectMocks
    private CourseController courseController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCourses() {
        List<Course> courses = new ArrayList<>();
        courses.add(new Course(1L, "Mathematics", new HashSet<>()));
        courses.add(new Course(2L, "Science", new HashSet<>()));
        when(courseService.getAllCourses()).thenReturn(courses);

        List<Course> result = courseController.getAllCourses();

        assertEquals(2, result.size());
        assertEquals("Mathematics", result.get(0).getName());
        assertEquals("Science", result.get(1).getName());
        verify(courseService, times(1)).getAllCourses();
    }

    @Test
    void testCreateCourse() {
        Course course = new Course(1L, "Mathematics", new HashSet<>());
        when(courseService.createCourse(course)).thenReturn(course);

        Course result = courseController.createCourse(course);

        assertEquals("Mathematics", result.getName());
        verify(courseService, times(1)).createCourse(course);
    }

    @Test
    void testFindCourse() {
        Long courseId = 1L;
        Course course = new Course(1L, "Mathematics", new HashSet<>());
        when(courseService.findCourse(courseId)).thenReturn(course);

        Course result = courseController.findCourse(courseId);

        assertEquals("Mathematics", result.getName());
        verify(courseService, times(1)).findCourse(courseId);
    }

    @Test
    void testUpdateCourse() {
        Long courseId = 1L;
        Course updatedCourse = new Course(1L, "Science", new HashSet<>());
        ResponseEntity<Course> responseEntity = new ResponseEntity<>(updatedCourse, HttpStatus.OK);
        when(courseService.updateCourse(courseId, updatedCourse)).thenReturn(responseEntity);

        ResponseEntity<Course> result = courseController.updateCourse(courseId, updatedCourse);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Science", result.getBody().getName());
        verify(courseService, times(1)).updateCourse(courseId, updatedCourse);
    }

    @Test
    void testDeleteCourse() {
        Long courseId = 1L;
        ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        when(courseService.deleteCourse(courseId)).thenReturn(responseEntity);

        ResponseEntity<Void> result = courseController.deleteCourse(courseId);

        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        verify(courseService, times(1)).deleteCourse(courseId);
    }
}

