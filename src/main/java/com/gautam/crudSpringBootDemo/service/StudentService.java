package com.gautam.crudSpringBootDemo.service;

import com.gautam.crudSpringBootDemo.entity.Student;
import com.gautam.crudSpringBootDemo.repository.StudentRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student studentReq) {
        // buisness logic
        // store to db
        Student studentRes = studentRepository.saveStudent(studentReq)
        return null;
    }

}
