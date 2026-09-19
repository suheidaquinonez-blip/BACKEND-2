package com.company.coursemanagement.domain.exception;

public class CourseNotFoundException extends ResourceNotFoundException {
    public CourseNotFoundException(Long id) {
        super("Course not found: " + id);
    }
}
