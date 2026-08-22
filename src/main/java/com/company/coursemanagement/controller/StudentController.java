package com.company.coursemanagement.controller;

import com.company.coursemanagement.application.dto.StudentDTO;
import com.company.coursemanagement.application.service.impl.StudentServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentServiceImpl studentServiceimpl;

    public StudentController(StudentServiceImpl studentService) {
        this.studentServiceimpl = studentService;
    }

    @GetMapping
    public List<StudentDTO> getAllStudents() {
        return studentServiceimpl.findAll();
    }

    @PostMapping
    public StudentDTO addStudent(@RequestBody StudentDTO studentDTO) {
        return studentServiceimpl.create(studentDTO);
    }
}
