package com.company.coursemanagement.application.dto.response;

import com.company.coursemanagement.domain.model.Student;

import java.time.LocalDate;

public record StudentResponseDto(

        Long id,
        String firstName,
        String lastName,
        String email,
        LocalDate birthDate

) {
    // Convierte la entidad (Student) en el DTO que se le muestra al cliente
    public static StudentResponseDto from(Student student) {
        return new StudentResponseDto(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getBirthDate()
        );
    }
}
