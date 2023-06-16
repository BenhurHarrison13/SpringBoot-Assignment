package com.springboot.academicmanagemt.service;

import com.springboot.academicmanagemt.entity.Student;
import com.springboot.academicmanagemt.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllStudents() {
        List<Student> Students = new ArrayList<>();
        Students.add(new Student(1L, "John", "Waugh", "john.waugh",  new HashSet<>()));
        Students.add(new Student(2L, "Steve", "Waugh", "steve.waugh",  new HashSet<>()));
        when(studentRepository.findAll()).thenReturn(Students);

        List<Student> result = studentService.getAllStudents();

        assertEquals(2, result.size());
        assertEquals(Students, result);
        verify(studentRepository, times(1)).findAll();
    }


    @Test
    void testSaveStudent() {
        Student Student = new Student(1L, "John", "Waugh", "john.waugh",  new HashSet<>());
        when(studentRepository.save(Student)).thenReturn(Student);

        Student result = studentService.createStudent(Student);

        assertEquals(Student, result);
        verify(studentRepository, times(1)).save(Student);
    }

}
