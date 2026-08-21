package com.company.coursemanagement.domain.repository;

import com.company.coursemanagement.domain.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    // INNER JOIN FETCH: Obtiene la inscripción con los detalles del estudiante y del curso
    @Query("SELECT e FROM Enrollment e JOIN FETCH e.student s JOIN FETCH e.course c WHERE s.id = :studentId")
    List<Enrollment> findEnrollmentsByStudentIdWithDetails(@Param("studentId") Long studentId);

    // LEFT JOIN FETCH: Consulta relacional por ID de curso
    @Query("SELECT e FROM Enrollment e LEFT JOIN FETCH e.student s LEFT JOIN FETCH e.course c WHERE c.id = :courseId")
    List<Enrollment> findEnrollmentsByCourseIdWithDetails(@Param("courseId") Long courseId);
}