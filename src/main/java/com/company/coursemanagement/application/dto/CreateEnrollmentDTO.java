package com.company.coursemanagement.application.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record CreateEnrollmentDTO(

        @NotNull(message = "studentId es obligatorio")
        Long studentId,

        @NotNull(message = "courseId es obligatorio")
        Long courseId,

        @PastOrPresent(message = "enrollmentDate no puede ser futura")
        LocalDate enrollmentDate

) {}