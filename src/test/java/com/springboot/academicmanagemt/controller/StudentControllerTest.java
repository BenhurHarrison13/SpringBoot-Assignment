package com.springboot.academicmanagemt.controller;

import com.springboot.academicmanagemt.entity.Address;
import com.springboot.academicmanagemt.entity.Student;
import com.springboot.academicmanagemt.rest.StudentController;
import com.springboot.academicmanagemt.service.StudentService;
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

class StudentControllerTest {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1L, "John", "Doe", "john@example.com",new HashSet<>(),new Address()));
        students.add(new Student(2L, "Jane" ,"Smith", "jane@example.com",new HashSet<>(),new Address()));
        when(studentService.getAllStudents()).thenReturn(students);
        List<Student> result = studentController.getAllStudents();
        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getFirstName());
        assertEquals("Jane", result.get(1).getFirstName());
        verify(studentService, times(1)).getAllStudents();
    }

    @Test
    void testCreateStudent() {
        Student student = new Student(1L, "John", "Doe", "john@example.com",new HashSet<>(),new Address());
        when(studentService.createStudent(student)).thenReturn(student);

        Student result = studentController.createStudent(student);

        assertEquals("John", result.getFirstName());
        assertEquals("john@example.com", result.getEmail());
        verify(studentService, times(1)).createStudent(student);
    }

    @Test
    void testFindStudent() {
        Long studentId = 1L;
        Student student = new Student(1L, "John", "Doe", "john@example.com",new HashSet<>(),new Address());
        when(studentService.findStudent(studentId)).thenReturn(student);

        Student result = studentController.findStudent(studentId);

        assertEquals("John", result.getFirstName());
        assertEquals("john@example.com", result.getEmail());
        verify(studentService, times(1)).findStudent(studentId);
    }


    @Test
    void testDeleteStudent() {
        Long studentId = 1L;
        when(studentService.deleteStudent(studentId)).thenReturn(new ResponseEntity<>(HttpStatus.NO_CONTENT));

        ResponseEntity<Void> result = studentController.deleteStudent(studentId);

        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        verify(studentService, times(1)).deleteStudent(studentId);
    }
}
