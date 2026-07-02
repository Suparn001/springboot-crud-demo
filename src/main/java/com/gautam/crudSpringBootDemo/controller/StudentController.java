package com.gautam.crudSpringBootDemo.controller;

import com.gautam.crudSpringBootDemo.entity.Student;
import com.gautam.crudSpringBootDemo.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        student.setDeleted(false);
        Student studentSaved = studentService.createStudent(student);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(studentSaved);
    }

    // read student

    @GetMapping("{id}")
    public ResponseEntity<Student> getOneStudent(
            @PathVariable Long id
    ) {
        Student studentRes = studentService.getSingleStudent(id);
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(studentRes);
        //or
        if (studentRes == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            //or
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity
                .ok(studentRes);
    }

    // get all
    @GetMapping
    public ResponseEntity<List<Student>> allStudents() {
        List<Student> studentList = studentService.getAllStudents();
        if (studentList.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(studentList);
        }

    }

    // update student
    @PutMapping("{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable Long id,
            @RequestBody Student student
    ) {
        Student updatedStudent = studentService.updateStudent(id, student);
        if (updatedStudent == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            //or
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity
                .ok(updatedStudent);
    }

    // delete student

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteStudent(
            @PathVariable Long id
    ) {
        boolean isDeleted = studentService.deleteStudent(id);
        if (isDeleted) {
            return ResponseEntity.ok("Record Deleted");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("{id}")
    public ResponseEntity<String> deleteStudentSoftly(
            @PathVariable Long id
    ) {
        Boolean isDeleted = studentService.deleteStudentSoftly(id);
        return null;
    }

}
