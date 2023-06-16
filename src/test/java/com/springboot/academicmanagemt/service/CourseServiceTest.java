package com.springboot.academicmanagemt.service;

import com.springboot.academicmanagemt.entity.Course;
import com.springboot.academicmanagemt.repository.CourseRepository;
import com.springboot.academicmanagemt.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private CourseService courseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCourses() {
        List<Course> courses = Arrays.asList(new Course(1L, "Mathematics",new HashSet<>()), new Course(2L, "Science",new HashSet<>()));

        when(courseRepository.findAll()).thenReturn(courses);

        List<Course> result = courseService.getAllCourses();

        assertEquals(2, result.size());
        assertEquals("Mathematics", result.get(0).getName());
        assertEquals("Science", result.get(1).getName());

        verify(courseRepository, times(1)).findAll();
    }

    @Test
    void testCreateCourse() {
        Course course = new Course(1L, "Mathematics",new HashSet<>());

        when(courseRepository.save(course)).thenReturn(course);

        Course result = courseService.createCourse(course);

        assertEquals(1L, result.getId());
        assertEquals("Mathematics", result.getName());

        verify(courseRepository, times(1)).save(course);
    }

    @Test
    void testFindCourse_ExistingCourse() {
        Course course = new Course(1L, "Mathematics",new HashSet<>());

        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));

        Course result = courseService.findCourse(1L);

        assertEquals(1L, result.getId());
        assertEquals("Mathematics", result.getName());

        verify(courseRepository, times(1)).findById(1L);
    }

    @Test
    void testFindCourse_NonExistingCourse() {
        when(courseRepository.findById(1L)).thenReturn(Optional.empty());

        Course result = courseService.findCourse(1L);

        assertNull(result);

        verify(courseRepository, times(1)).findById(1L);
    }


    @Test
    void testUpdateCourse_ExistingCourse() {
        Course existingCourse = new Course(1L, "Mathematics",new HashSet<>());

        Course updatedCourse = new Course(1L, "Science",new HashSet<>());

        when(courseRepository.findById(1L)).thenReturn(Optional.of(existingCourse));
        when(courseRepository.save(existingCourse)).thenReturn(existingCourse);


        ResponseEntity<Course> result = courseService.updateCourse(1L, updatedCourse);

        assertEquals("Science", Objects.requireNonNull(result.getBody()).getName());

        verify(courseRepository, times(1)).findById(1L);
        verify(courseRepository, times(1)).save(existingCourse);
    }


    @Test
    void testDeleteCourse_NonExistingCourse() {

        when(courseRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Void> result = courseService.deleteCourse(1L);

        assertTrue(result.getStatusCode().isError());

        verify(courseRepository, times(1)).findById(1L);
        verify(courseRepository, times(0)).delete(any(Course.class));
    }

}