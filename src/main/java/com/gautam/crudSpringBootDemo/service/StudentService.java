package com.gautam.crudSpringBootDemo.service;

import com.gautam.crudSpringBootDemo.entity.Student;
import com.gautam.crudSpringBootDemo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student studentReq) {
        // buisness logic
        // store to db
        Student studentRes = studentRepository.save(studentReq);
        return studentRes;
    }

    public Student getSingleStudent(Long id) {

//        Optional<Student> studentRes = studentRepository.findById(id);
        Optional<Student> studentRes = studentRepository.findByIdAndDeletedIsFalse(id);
        if (studentRes.isPresent()) {
            return studentRes.get();
        } else {
            return null;
        }
    }

    public List<Student> getAllStudents() {

        List<Student> studentList = studentRepository.findByDeletedIsFalse();
//        List<Student> studentList = studentRepository.findAll();
        return studentList;
    }

    public Student updateStudent(Long id, Student student) {
        Optional<Student> studentRes = studentRepository.findByIdAndDeletedIsFalse(id);
        if (studentRes.isEmpty()) {
            return null;
        } else {
            Student studentToBeUpdated = studentRes.get();
            studentToBeUpdated.setName(student.getName());
            studentToBeUpdated.setAge(student.getAge());
            studentToBeUpdated.setSubject(student.getSubject());
            studentToBeUpdated.setEmail(student.getEmail());
            studentToBeUpdated.setDeleted(false);
            return studentRepository.save(studentToBeUpdated);

        }
    }

    public Boolean deleteStudent(Long id) {
        Boolean isStudent = studentRepository.existsById(id);
        if (isStudent) {
            studentRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Boolean deleteStudentSoftly(Long id) {
        // get the record
        Optional<Student> existingStudent = studentRepository.findByIdAndDeletedIsFalse(id);
        if (existingStudent.isEmpty()) {
            return false;
        } else {
            Student studentToSave = existingStudent.get();
            studentToSave.setDeleted(true);
            studentRepository.save(existingStudent.get());
            return true;
        }
        // update is_Deleted to 0
        // save
    }
}
