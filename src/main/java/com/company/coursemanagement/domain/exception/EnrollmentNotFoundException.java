package com.company.coursemanagement.domain.exception;

public class EnrollmentNotFoundException extends ResourceNotFoundException {
    public EnrollmentNotFoundException(Long id) {
        super("Enrollment not found: " + id);
    }
}
