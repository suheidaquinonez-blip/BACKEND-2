package com.company.coursemanagement.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateCourseDTO(

        @NotBlank(message = "code es obligatorio")
        String code,

        @NotBlank(message = "name es obligatorio")
        String name,

        String description,

        @NotNull(message = "maxCapacity es obligatorio")
        @Positive(message = "maxCapacity debe ser mayor que cero")
        Integer maxCapacity

) {}