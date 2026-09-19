package com.company.coursemanagement.controller;

import com.company.coursemanagement.application.dto.CreateStudentDTO;
import com.company.coursemanagement.application.dto.PatchStudentDto;
import com.company.coursemanagement.application.dto.UpdateStudentDto;
import com.company.coursemanagement.application.dto.response.StudentResponseDto;
import com.company.coursemanagement.application.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<StudentResponseDto>> getAllStudents() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentResponseDto> getById(@PathVariable Long studentId) {
        return ResponseEntity.ok(studentService.findById(studentId));
    }

    @PostMapping
    public ResponseEntity<StudentResponseDto> create(@Valid @RequestBody CreateStudentDTO dto) {
        StudentResponseDto created = studentService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<StudentResponseDto> update(
            @PathVariable Long studentId,
            @Valid @RequestBody UpdateStudentDto dto) {

        return ResponseEntity.ok(studentService.update(studentId, dto));
    }

    @PatchMapping("/{studentId}")
    public ResponseEntity<StudentResponseDto> patch(
            @PathVariable Long studentId,
            @Valid @RequestBody PatchStudentDto dto) {

        return ResponseEntity.ok(studentService.patch(studentId, dto));
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> delete(@PathVariable Long studentId) {
        studentService.delete(studentId);
        return ResponseEntity.noContent().build();
    }
}
