package com.company.coursemanagement.domain.exception;

public class CourseCodeAlreadyExistsException extends ResourceConflictException {
    public CourseCodeAlreadyExistsException(String code) {
        super("Ya existe un curso con el código: " + code);
    }
}
