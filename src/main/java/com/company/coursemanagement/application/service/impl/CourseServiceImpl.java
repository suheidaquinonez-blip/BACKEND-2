package com.company.coursemanagement.application.service.impl;

import com.company.coursemanagement.application.dto.CreateCourseDTO;
import com.company.coursemanagement.application.dto.UpdateCourseDTO;
import com.company.coursemanagement.application.dto.response.CourseResponseDto;
import com.company.coursemanagement.application.service.CourseService;
import com.company.coursemanagement.domain.exception.CourseCodeAlreadyExistsException;
import com.company.coursemanagement.domain.exception.CourseNotFoundException;
import com.company.coursemanagement.domain.model.Course;
import com.company.coursemanagement.domain.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public CourseResponseDto create(CreateCourseDTO dto) {
        if (courseRepository.existsByCode(dto.code())) {
            throw new CourseCodeAlreadyExistsException(dto.code());
        }

        Course course = new Course(null, dto.code(), dto.name(), dto.description(), dto.maxCapacity());
        Course saved = courseRepository.save(course);
        return CourseResponseDto.from(saved);
    }

    @Override
    public CourseResponseDto findById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));
        return CourseResponseDto.from(course);
    }

    @Override
    public List<CourseResponseDto> findAll() {
        return courseRepository.findAll().stream()
                .map(CourseResponseDto::from)
                .toList();
    }

    @Override
    public CourseResponseDto update(Long id, UpdateCourseDTO dto) {
        if (!courseRepository.existsById(id)) {
            throw new CourseNotFoundException(id);
        }

        courseRepository.findByCode(dto.code())
                .filter(existing -> !existing.getId().equals(id))
                .ifPresent(existing -> {
                    throw new CourseCodeAlreadyExistsException(dto.code());
                });

        Course course = new Course(id, dto.code(), dto.name(), dto.description(), dto.maxCapacity());
        Course updated = courseRepository.save(course);
        return CourseResponseDto.from(updated);
    }

    @Override
    public void delete(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new CourseNotFoundException(id);
        }
        courseRepository.deleteById(id);
    }
}
