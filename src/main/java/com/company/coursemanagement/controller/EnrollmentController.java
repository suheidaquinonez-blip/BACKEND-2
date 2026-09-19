package com.company.coursemanagement.controller;

import com.company.coursemanagement.application.dto.CreateEnrollmentDTO;
import com.company.coursemanagement.application.dto.response.EnrollmentResponseDto;
import com.company.coursemanagement.application.service.EnrollmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @GetMapping
    public ResponseEntity<List<EnrollmentResponseDto>> getAllEnrollments() {
        return ResponseEntity.ok(enrollmentService.findAll());
    }

    @GetMapping("/{enrollmentId}")
    public ResponseEntity<EnrollmentResponseDto> getById(@PathVariable Long enrollmentId) {
        return ResponseEntity.ok(enrollmentService.findById(enrollmentId));
    }

    @PostMapping
    public ResponseEntity<EnrollmentResponseDto> enroll(@Valid @RequestBody CreateEnrollmentDTO dto) {
        EnrollmentResponseDto created = enrollmentService.enrollStudent(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @DeleteMapping("/{enrollmentId}")
    public ResponseEntity<Void> cancel(@PathVariable Long enrollmentId) {
        enrollmentService.cancelEnrollment(enrollmentId);
        return ResponseEntity.noContent().build();
    }
}
