package com.company.coursemanagement.application.service.impl;

import com.company.coursemanagement.application.dto.CreateEnrollmentDTO;
import com.company.coursemanagement.application.dto.response.EnrollmentResponseDto;
import com.company.coursemanagement.application.service.EnrollmentService;
import com.company.coursemanagement.domain.exception.BusinessException;
import com.company.coursemanagement.domain.exception.CourseNotFoundException;
import com.company.coursemanagement.domain.exception.EnrollmentAlreadyExistsException;
import com.company.coursemanagement.domain.exception.EnrollmentNotFoundException;
import com.company.coursemanagement.domain.exception.StudentNotFoundException;
import com.company.coursemanagement.domain.model.Course;
import com.company.coursemanagement.domain.model.Enrollment;
import com.company.coursemanagement.domain.model.EnrollmentStatus;
import com.company.coursemanagement.domain.model.Student;
import com.company.coursemanagement.domain.repository.CourseRepository;
import com.company.coursemanagement.domain.repository.EnrollmentRepository;
import com.company.coursemanagement.domain.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository, StudentRepository studentRepository, CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public EnrollmentResponseDto enrollStudent(CreateEnrollmentDTO dto) {
        Student student = studentRepository.findById(dto.studentId())
                .orElseThrow(() -> new StudentNotFoundException(dto.studentId()));

        Course course = courseRepository.findById(dto.courseId())
                .orElseThrow(() -> new CourseNotFoundException(dto.courseId()));

        boolean yaInscrito = enrollmentRepository.findEnrollmentsByStudentIdWithDetails(dto.studentId()).stream()
                .anyMatch(e -> e.getCourseId().equals(dto.courseId()) && e.getStatus() == EnrollmentStatus.ACTIVE);
        if (yaInscrito) {
            throw new EnrollmentAlreadyExistsException(dto.studentId(), dto.courseId());
        }

        long activeCount = enrollmentRepository.countByCourse_IdAndStatus(dto.courseId(), EnrollmentStatus.ACTIVE);
        if (activeCount >= course.getMaxCapacity()) {
            throw new BusinessException("El curso ha alcanzado su capacidad máxima (" + course.getMaxCapacity() + ")");
        }

        LocalDate enrollmentDate = dto.enrollmentDate() != null ? dto.enrollmentDate() : LocalDate.now();
        Enrollment enrollment = new Enrollment(null, student, course, enrollmentDate, EnrollmentStatus.ACTIVE);
        Enrollment saved = enrollmentRepository.save(enrollment);
        return EnrollmentResponseDto.from(saved);
    }

    @Override
    public EnrollmentResponseDto findById(Long id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new EnrollmentNotFoundException(id));
        return EnrollmentResponseDto.from(enrollment);
    }

    @Override
    public List<EnrollmentResponseDto> findAll() {
        return enrollmentRepository.findAll().stream()
                .map(EnrollmentResponseDto::from)
                .toList();
    }

    @Override
    public void cancelEnrollment(Long id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new EnrollmentNotFoundException(id));
        enrollment.setStatus(EnrollmentStatus.CANCELLED);
        enrollmentRepository.save(enrollment);
    }
}
