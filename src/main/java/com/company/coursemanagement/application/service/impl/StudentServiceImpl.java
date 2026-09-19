package com.company.coursemanagement.application.service.impl;

import com.company.coursemanagement.application.dto.CreateStudentDTO;
import com.company.coursemanagement.application.dto.PatchStudentDto;
import com.company.coursemanagement.application.dto.UpdateStudentDto;
import com.company.coursemanagement.application.dto.response.StudentResponseDto;
import com.company.coursemanagement.application.service.StudentService;
import com.company.coursemanagement.domain.exception.StudentEmailAlreadyExistsException;
import com.company.coursemanagement.domain.exception.StudentNotFoundException;
import com.company.coursemanagement.domain.model.Student;
import com.company.coursemanagement.domain.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentResponseDto create(CreateStudentDTO dto) {
        if (studentRepository.existsByEmail(dto.email())) {
            throw new StudentEmailAlreadyExistsException(dto.email());
        }

        Student student = new Student();
        student.setFirstName(dto.firstName());
        student.setLastName(dto.lastName());
        student.setEmail(dto.email());
        student.setBirthDate(dto.birthDate());

        Student saved = studentRepository.save(student);
        return StudentResponseDto.from(saved);
    }

    @Override
    public StudentResponseDto findById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
        return StudentResponseDto.from(student);
    }

    @Override
    public List<StudentResponseDto> findAll() {
        return studentRepository.findAll()
                .stream()
                .map(StudentResponseDto::from)
                .toList();
    }

    @Override
    public StudentResponseDto update(Long id, UpdateStudentDto dto) {
        Student existing = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));

        if (!existing.getEmail().equalsIgnoreCase(dto.email())
                && studentRepository.existsByEmail(dto.email())) {
            throw new StudentEmailAlreadyExistsException(dto.email());
        }

        existing.setFirstName(dto.firstName());
        existing.setLastName(dto.lastName());
        existing.setEmail(dto.email());
        existing.setBirthDate(dto.birthDate());

        Student updated = studentRepository.save(existing);
        return StudentResponseDto.from(updated);
    }

    @Override
    public StudentResponseDto patch(Long id, PatchStudentDto dto) {
        Student existing = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));

        if (dto.firstName() != null) {
            existing.setFirstName(dto.firstName());
        }

        if (dto.lastName() != null) {
            existing.setLastName(dto.lastName());
        }

        if (dto.email() != null) {
            if (!existing.getEmail().equalsIgnoreCase(dto.email())
                    && studentRepository.existsByEmail(dto.email())) {
                throw new StudentEmailAlreadyExistsException(dto.email());
            }
            existing.setEmail(dto.email());
        }

        if (dto.birthDate() != null) {
            existing.setBirthDate(dto.birthDate());
        }

        Student updated = studentRepository.save(existing);
        return StudentResponseDto.from(updated);
    }

    @Override
    public void delete(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException(id);
        }
        studentRepository.deleteById(id);
    }
}
