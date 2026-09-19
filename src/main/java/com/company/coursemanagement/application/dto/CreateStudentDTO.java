package com.company.coursemanagement.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record CreateStudentDTO(

        @NotBlank(message = "firstName es obligatorio")
        String firstName,

        @NotBlank(message = "lastName es obligatorio")
        String lastName,

        @NotBlank(message = "email es obligatorio")
        @Email(message = "email inválido")
        String email,

        @NotNull(message = "birthDate es obligatoria")
        @Past(message = "birthDate no puede ser futura")
        LocalDate birthDate

) {}