package com.springboot.academicmanagemt.repository;

import com.springboot.academicmanagemt.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
