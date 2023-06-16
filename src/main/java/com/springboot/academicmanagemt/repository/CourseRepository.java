package com.springboot.academicmanagemt.repository;

import com.springboot.academicmanagemt.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
