package com.company.coursemanagement.domain.repository;

import com.company.coursemanagement.domain.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    boolean existsByCode(String code);
    Optional<Course> findByCode(String code);
    // Al extender de JpaRepository, Spring ya te regala automáticamente:
    // save(), findById(), findAll(), deleteById(), existsById() y más.
}