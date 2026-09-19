package com.company.coursemanagement.controller;

import com.company.coursemanagement.application.dto.CreateCourseDTO;
import com.company.coursemanagement.application.dto.UpdateCourseDTO;
import com.company.coursemanagement.application.dto.response.CourseResponseDto;
import com.company.coursemanagement.application.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<CourseResponseDto>> getAllCourses() {
        return ResponseEntity.ok(courseService.findAll());
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<CourseResponseDto> getById(@PathVariable Long courseId) {
        return ResponseEntity.ok(courseService.findById(courseId));
    }

    @PostMapping
    public ResponseEntity<CourseResponseDto> create(@Valid @RequestBody CreateCourseDTO dto) {
        CourseResponseDto created = courseService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<CourseResponseDto> update(
            @PathVariable Long courseId,
            @Valid @RequestBody UpdateCourseDTO dto) {

        return ResponseEntity.ok(courseService.update(courseId, dto));
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<Void> delete(@PathVariable Long courseId) {
        courseService.delete(courseId);
        return ResponseEntity.noContent().build();
    }
}
