package com.company.coursemanagement.application.dto.response;

import com.company.coursemanagement.domain.model.Enrollment;
import com.company.coursemanagement.domain.model.EnrollmentStatus;

import java.time.LocalDate;

public record EnrollmentResponseDto(

        Long id,
        Long studentId,
        Long courseId,
        LocalDate enrollmentDate,
        EnrollmentStatus status

) {
    public static EnrollmentResponseDto from(Enrollment enrollment) {
        return new EnrollmentResponseDto(
                enrollment.getId(),
                enrollment.getStudentId(),
                enrollment.getCourseId(),
                enrollment.getEnrollmentDate(),
                enrollment.getStatus()
        );
    }
}
