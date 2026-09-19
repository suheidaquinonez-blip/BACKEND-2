package com.company.coursemanagement.domain.exception;

public class EnrollmentAlreadyExistsException extends ResourceConflictException {
    public EnrollmentAlreadyExistsException(Long studentId, Long courseId) {
        super("El estudiante " + studentId + " ya está inscrito activamente en el curso " + courseId);
    }
}
