package com.company.coursemanagement.domain.exception;

public class StudentEmailAlreadyExistsException extends ResourceConflictException {
    public StudentEmailAlreadyExistsException(String email) {
        super("Ya existe un estudiante con el email: " + email);
    }
}
