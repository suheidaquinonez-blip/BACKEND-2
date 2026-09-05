package com.company.coursemanagement.controller;

import com.company.coursemanagement.application.dto.EnrollmentDTO;
import com.company.coursemanagement.application.service.impl.EnrollmentServiceImpl;
import com.company.coursemanagement.domain.exception.BusinessException;
import com.company.coursemanagement.domain.exception.CourseNotFoundException;
import com.company.coursemanagement.domain.exception.EnrollmentNotFoundException;
import com.company.coursemanagement.domain.exception.StudentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    private final EnrollmentServiceImpl enrollmentServiceImpl;

    public EnrollmentController(EnrollmentServiceImpl enrollmentServiceImpl) {
        this.enrollmentServiceImpl = enrollmentServiceImpl;
    }

    @GetMapping
    public ResponseEntity<Object> getAllEnrollments() {
        try {
            return ResponseEntity.ok(enrollmentServiceImpl.findAll());
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{enrollmentId}")
    public ResponseEntity<Object> getById(@PathVariable Long enrollmentId) {
        try {
            return ResponseEntity.ok(enrollmentServiceImpl.findById(enrollmentId));
        } catch (EnrollmentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> enroll(@RequestBody EnrollmentDTO enrollmentDTO) {
        try {
            EnrollmentDTO created = enrollmentServiceImpl.enrollStudent(
                    enrollmentDTO.getStudentId(), enrollmentDTO.getCourseId());
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (StudentNotFoundException | CourseNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (BusinessException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{enrollmentId}")
    public ResponseEntity<Object> cancel(@PathVariable Long enrollmentId) {
        try {
            enrollmentServiceImpl.cancelEnrollment(enrollmentId);
            return ResponseEntity.noContent().build();
        } catch (EnrollmentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}