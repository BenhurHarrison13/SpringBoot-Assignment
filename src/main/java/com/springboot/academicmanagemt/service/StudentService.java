package com.springboot.academicmanagemt.service;

import com.springboot.academicmanagemt.entity.Student;
import com.springboot.academicmanagemt.repository.CourseRepository;
import com.springboot.academicmanagemt.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository, AddressService addressService, CourseService courseService) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student findStudent(Long studentId) {
        return studentRepository.findById(studentId).orElse(null);
    }

    public ResponseEntity<Student> updateStudent(Long id, Student updatedStudent) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Student existingStudent = optionalStudent.get();
        existingStudent.setFirstName(updatedStudent.getFirstName());
        existingStudent.setLastName(updatedStudent.getLastName());
        existingStudent.setEmail(updatedStudent.getEmail());

        existingStudent.setCourses(updatedStudent.getCourses());

        Student updatedStudentEntity = studentRepository.save(existingStudent);
        return ResponseEntity.ok(updatedStudentEntity);
    }

    public ResponseEntity<Void> deleteStudent(Long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Student student = optionalStudent.get();
        studentRepository.delete(student);

        return ResponseEntity.noContent().build();
    }
}
