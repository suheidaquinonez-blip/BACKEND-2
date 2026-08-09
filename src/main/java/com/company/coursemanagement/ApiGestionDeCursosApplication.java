package com.company.coursemanagement;

import com.company.coursemanagement.application.service.CourseService;
import com.company.coursemanagement.application.service.EnrollmentService;
import com.company.coursemanagement.application.service.StudentService;
import com.company.coursemanagement.application.service.impl.CourseServiceImpl;
import com.company.coursemanagement.application.service.impl.EnrollmentServiceImpl;
import com.company.coursemanagement.application.service.impl.StudentServiceImpl;
import com.company.coursemanagement.domain.repository.CourseRepository;
import com.company.coursemanagement.domain.repository.EnrollmentRepository;
import com.company.coursemanagement.domain.repository.StudentRepository;
import com.company.coursemanagement.infrastructure.persistence.InMemoryCourseRepository;
import com.company.coursemanagement.infrastructure.persistence.InMemoryEnrollmentRepository;
import com.company.coursemanagement.infrastructure.persistence.InMemoryStudentRepository;
import com.company.coursemanagement.presentation.ConsoleMenu;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiGestionDeCursosApplication implements CommandLineRunner {

    public static void main(String[] args) {
        // 1. Inicia el framework Spring Boot
        SpringApplication.run(ApiGestionDeCursosApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}