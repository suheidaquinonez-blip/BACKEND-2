package com.company.coursemanagement.application.service;

import com.company.coursemanagement.application.dto.CreateCourseDTO;
import com.company.coursemanagement.application.dto.UpdateCourseDTO;
import com.company.coursemanagement.application.dto.response.CourseResponseDto;

import java.util.List;

public interface CourseService {
    CourseResponseDto create(CreateCourseDTO dto);
    CourseResponseDto findById(Long id);
    List<CourseResponseDto> findAll();
    CourseResponseDto update(Long id, UpdateCourseDTO dto);
    void delete(Long id);
}
