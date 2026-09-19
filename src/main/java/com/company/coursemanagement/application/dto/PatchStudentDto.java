package com.company.coursemanagement.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record PatchStudentDto(

        String firstName,

        String lastName,

        @Email(message = "email inválido")
        String email,

        @Past(message = "birthDate no puede ser futura")
        LocalDate birthDate

) {}
