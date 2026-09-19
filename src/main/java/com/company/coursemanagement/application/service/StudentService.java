package com.company.coursemanagement.application.service;

import com.company.coursemanagement.application.dto.CreateStudentDTO;
import com.company.coursemanagement.application.dto.PatchStudentDto;
import com.company.coursemanagement.application.dto.UpdateStudentDto;
import com.company.coursemanagement.application.dto.response.StudentResponseDto;

import java.util.List;

public interface StudentService {
    StudentResponseDto create(CreateStudentDTO dto);
    StudentResponseDto findById(Long id);
    List<StudentResponseDto> findAll();
    StudentResponseDto update(Long id, UpdateStudentDto dto);
    StudentResponseDto patch(Long id, PatchStudentDto dto);
    void delete(Long id);
}
