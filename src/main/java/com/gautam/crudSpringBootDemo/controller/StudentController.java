package com.gautam.crudSpringBootDemo.controller;

import com.gautam.crudSpringBootDemo.dto.CreateStudentRequestDTO;
import com.gautam.crudSpringBootDemo.dto.CreateStudentResponseDTO;
import com.gautam.crudSpringBootDemo.dto.UpdateStudentRequestDTO;
import com.gautam.crudSpringBootDemo.dto.UpdateStudentResponseDTO;
import com.gautam.crudSpringBootDemo.service.StudentService;
import jakarta.validation.Valid;
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
    public ResponseEntity<CreateStudentResponseDTO> createStudent(
            @Valid @RequestBody CreateStudentRequestDTO createStudentRequestDTO
    ) {

        CreateStudentResponseDTO studentSaved = studentService.createStudent(createStudentRequestDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(studentSaved);
    }

    // read student

    @GetMapping("{id}")
    public ResponseEntity<CreateStudentResponseDTO> getOneStudent(
            @PathVariable Long id
    ) {
        CreateStudentResponseDTO studentRes = studentService.getSingleStudent(id);
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(studentRes);
        //or
//        if (studentRes == null) {
////            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//            //or
//            return ResponseEntity.notFound().build();
//        }
        return ResponseEntity.ok(studentRes);
    }

    // get all
    @GetMapping
    public ResponseEntity<List<CreateStudentResponseDTO>> allStudents() {
        List<CreateStudentResponseDTO> studentList = studentService.getAllStudents();
//        if (studentList.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        } else {
        return ResponseEntity.ok(studentList);
//        }

    }

    // update student
    @PutMapping("{id}")
    public ResponseEntity<UpdateStudentResponseDTO> updateStudent(
            @PathVariable Long id,
            @RequestBody UpdateStudentRequestDTO updateStudentRequestDTO
    ) {
        UpdateStudentResponseDTO updatedStudent = studentService.updateStudent(id, updateStudentRequestDTO);
//        if (updatedStudent == null) {
////            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//            //or
//            return ResponseEntity.notFound().build();
//        }
        return ResponseEntity
                .ok(updatedStudent);
    }

    // delete student

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteStudent(
            @PathVariable Long id
    ) {
        studentService.deleteStudent(id);
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
// or
        return ResponseEntity.noContent().build();
//        if (isDeleted) {
//            return ResponseEntity.ok("Record Deleted");
//        } else {
//        return ResponseEntity.notFound().build();
//        }
    }

    @PatchMapping("{id}")
    public ResponseEntity<String> deleteStudentSoftly(
            @PathVariable Long id
    ) {
        studentService.deleteStudentSoftly(id);
        return ResponseEntity.noContent().build();
    }

}
