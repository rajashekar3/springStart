package com.example.springStart;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping("/students")
    public Page<StudentResponse> getStudents(Pageable pageable) {
        return service.getAllStudents(pageable);
    }

    @PostMapping("/students")
    @ResponseStatus(HttpStatus.CREATED)
    public StudentResponse addStudent(@Valid @RequestBody StudentRequest request) {
        return service.saveStudent(request);
    }

    @GetMapping("/students/{id}")
    public StudentResponse getStudent(@PathVariable Long id) {
        return service.getStudentById(id);
    }
    @DeleteMapping("/students/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable Long id) {
        service.deleteStudent(id);
    }
    }