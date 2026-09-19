package com.company.coursemanagement.application.dto.response;

import com.company.coursemanagement.domain.model.Course;

public record CourseResponseDto(

        Long id,
        String code,
        String name,
        String description,
        Integer maxCapacity

) {
    public static CourseResponseDto from(Course course) {
        return new CourseResponseDto(
                course.getId(),
                course.getCode(),
                course.getName(),
                course.getDescription(),
                course.getMaxCapacity()
        );
    }
}
