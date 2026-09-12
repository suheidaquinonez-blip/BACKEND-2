package com.company.coursemanagement.controller;

import com.company.coursemanagement.application.dto.StudentDTO;
import com.company.coursemanagement.application.service.impl.StudentServiceImpl;
import com.company.coursemanagement.domain.exception.BusinessException;
import com.company.coursemanagement.domain.exception.StudentNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentServiceImpl studentServiceimpl;

    public StudentController(StudentServiceImpl studentService) {
        this.studentServiceimpl = studentService;

    }

    @GetMapping
    public ResponseEntity<Object> getAllStudents() {
        try {
            return ResponseEntity.ok(studentServiceimpl.findAll());
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Object> getById(@PathVariable Long studentId) {
        try {
            return ResponseEntity.ok(studentServiceimpl.findById(studentId));
        } catch (StudentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> addStudent(@RequestBody StudentDTO studentDTO) {
        try {
            StudentDTO created = studentServiceimpl.create(studentDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (BusinessException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<Object> update(@PathVariable Long studentId,@Valid  @RequestBody StudentDTO studentDTO) {
        try {
            StudentDTO updated = studentServiceimpl.update(studentId, studentDTO);
            return ResponseEntity.ok(updated);
        } catch (StudentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (BusinessException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Object> delete(@PathVariable Long studentId) {
        try {
            studentServiceimpl.delete(studentId);
            return ResponseEntity.noContent().build();
        } catch (StudentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}