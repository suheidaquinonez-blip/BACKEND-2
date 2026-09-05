package com.company.coursemanagement.controller;

import com.company.coursemanagement.application.dto.CourseDTO;
import com.company.coursemanagement.application.service.impl.CourseServiceImpl;
import com.company.coursemanagement.domain.exception.BusinessException;
import com.company.coursemanagement.domain.exception.CourseNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseServiceImpl courseServiceImpl;

    public CourseController(CourseServiceImpl courseServiceImpl) {
        this.courseServiceImpl = courseServiceImpl;
    }

    @GetMapping
    public ResponseEntity<Object> getAllCourses() {
        try {
            return ResponseEntity.ok(courseServiceImpl.findAll());
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<Object> getById(@PathVariable Long courseId) {
        try {
            return ResponseEntity.ok(courseServiceImpl.findById(courseId));
        } catch (CourseNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> addCourse(@RequestBody CourseDTO courseDTO) {
        try {
            CourseDTO created = courseServiceImpl.create(courseDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (BusinessException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<Object> update(@PathVariable Long courseId, @RequestBody CourseDTO courseDTO) {
        try {
            CourseDTO updated = courseServiceImpl.update(courseId, courseDTO);
            return ResponseEntity.ok(updated);
        } catch (CourseNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (BusinessException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<Object> delete(@PathVariable Long courseId) {
        try {
            courseServiceImpl.delete(courseId);
            return ResponseEntity.noContent().build();
        } catch (CourseNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}