package com.company.coursemanagement.application.service;

import com.company.coursemanagement.application.dto.CreateEnrollmentDTO;
import com.company.coursemanagement.application.dto.response.EnrollmentResponseDto;

import java.util.List;

public interface EnrollmentService {
    EnrollmentResponseDto enrollStudent(CreateEnrollmentDTO dto);
    EnrollmentResponseDto findById(Long id);
    List<EnrollmentResponseDto> findAll();
    void cancelEnrollment(Long id);
}
