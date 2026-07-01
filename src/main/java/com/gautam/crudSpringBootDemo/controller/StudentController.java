package com.gautam.crudSpringBootDemo.controller;

import com.gautam.crudSpringBootDemo.entity.Student;
import com.gautam.crudSpringBootDemo.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // create student
    @PostMapping
    public ResponseEntity<Student> createStudent(
            @RequestBody Student student
    ) {
        Student studentSaved = studentService.createStudent(student);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(studentSaved);
    }

    // read student

    // update student

    // delete student

}
