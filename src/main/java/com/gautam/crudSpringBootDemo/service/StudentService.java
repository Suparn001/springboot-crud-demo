package com.gautam.crudSpringBootDemo.service;

import com.gautam.crudSpringBootDemo.dto.CreateStudentRequestDTO;
import com.gautam.crudSpringBootDemo.dto.CreateStudentResponseDTO;
import com.gautam.crudSpringBootDemo.dto.UpdateStudentRequestDTO;
import com.gautam.crudSpringBootDemo.dto.UpdateStudentResponseDTO;
import com.gautam.crudSpringBootDemo.entity.Student;
import com.gautam.crudSpringBootDemo.exception.DuplicateResourceException;
import com.gautam.crudSpringBootDemo.exception.ResourceNotFoundException;
import com.gautam.crudSpringBootDemo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public CreateStudentResponseDTO createStudent(CreateStudentRequestDTO studentReqDTO) {
        // map to Entity as in db entity is saved
        Student student = mapToEntity(studentReqDTO);
        if (emailExists(student)) {
            throw new DuplicateResourceException("Student with email" + student.getEmail() + " already exists");
        }
        Student studentRes = studentRepository.save(student);
        return mapToDTO(studentRes);
    }

    private boolean emailExists(Student student) {
        return studentRepository.existByEmail(student.getEmail());
    }

    private Student mapToEntity(CreateStudentRequestDTO studentReqDTO) {
        Student student = new Student();
        student.setName(studentReqDTO.getName());
        student.setAge(studentReqDTO.getAge());
        student.setEmail(studentReqDTO.getEmail());
        student.setRollNo(studentReqDTO.getRollNo());
        student.setSubject(studentReqDTO.getSubject());
        student.setDeleted(false);
        return student;

        // this not good pattern , afterwards we will use builder design pattern
    }

    private CreateStudentResponseDTO mapToDTO(Student studentRes) {
        CreateStudentResponseDTO createStudentResponseDTO = new CreateStudentResponseDTO();
        createStudentResponseDTO.setId(studentRes.getId());
        createStudentResponseDTO.setName(studentRes.getName());
        createStudentResponseDTO.setAge(studentRes.getAge());
        createStudentResponseDTO.setEmail(studentRes.getEmail());
        createStudentResponseDTO.setRollNo(studentRes.getRollNo());
        createStudentResponseDTO.setSubject(studentRes.getSubject());
        createStudentResponseDTO.setCreatedAt(LocalDateTime.now());
        createStudentResponseDTO.setUpdatedAt(LocalDateTime.now());
        createStudentResponseDTO.setMessage("Student Saved Successfully");
        return createStudentResponseDTO;
    }

    public CreateStudentResponseDTO getSingleStudent(Long id) {
//
////        Optional<Student> studentRes = studentRepository.findById(id);
//        Optional<Student> studentRes = studentRepository.findByIdAndDeletedIsFalse(id);
////        if (studentRes.isPresent()) {
////            return mapToDTO(studentRes.get());
////        } else {
////            return null;
////        }
//        return mapToDTO(studentRes.get());
        Student studentResp = studentRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student with id" + id + " not found"));

        return mapToDTO(studentResp);
    }

    public List<CreateStudentResponseDTO> getAllStudents() {

        List<Student> studentList = studentRepository.findByDeletedIsFalse();

//        List<Student> studentList = studentRepository.findAll();
        return studentList.stream().
                map(this::mapToDTO)
                .toList()
                ;
    }

    public UpdateStudentResponseDTO updateStudent(Long id, UpdateStudentRequestDTO updateStudentRequestDTO) {
        Student studentToBeUpdated = studentRepository
                .findByIdAndDeletedIsFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student with id" + id + " not found"));
//        if (existingStudent.isEmpty()) {
//            return null;
//        } else {
        studentToBeUpdated.setName(updateStudentRequestDTO.getName());
        studentToBeUpdated.setAge(updateStudentRequestDTO.getAge());
        studentToBeUpdated.setSubject(updateStudentRequestDTO.getSubject());
        studentToBeUpdated.setUpdatedAt(LocalDateTime.now());
        Student updatedStudent = studentRepository.save(studentToBeUpdated);

        return mapToUpdateDTO(updatedStudent);
//        }
    }

    private UpdateStudentResponseDTO mapToUpdateDTO(Student updatedStudent) {
        UpdateStudentResponseDTO updateStudentResponseDTO = new UpdateStudentResponseDTO();
        updateStudentResponseDTO.setId(updatedStudent.getId());
        updateStudentResponseDTO.setName(updatedStudent.getName());
        updateStudentResponseDTO.setAge(updatedStudent.getAge());
        updateStudentResponseDTO.setEmail(updatedStudent.getEmail());
        updateStudentResponseDTO.setRollNo(updatedStudent.getRollNo());
        updateStudentResponseDTO.setSubject(updatedStudent.getSubject());
        updateStudentResponseDTO.setUpdatedAt(LocalDateTime.now());
        updateStudentResponseDTO.setMessage("Student Updated Successfully");
        return updateStudentResponseDTO;
    }

    public void deleteStudent(Long id) {
        Student studentToBeDeleted = studentRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student with id" + id + "not found;"));
        studentRepository.delete(studentToBeDeleted);
        //        if (isStudent) {
//            return true;
//        } else {
//            return false;
//        }
    }

    public void deleteStudentSoftly(Long id) {
        // get the record
        Student studentToSave = studentRepository.findByIdAndDeletedIsFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student with id" + id + "not found"));
//        if (existingStudent.isEmpty()) {
//            return false;
//        } else {
//        Student studentToSave = existingStudent;
        studentToSave.setDeleted(true);
        studentRepository.save(studentToSave);
//        return true;
//        }
        // update is_Deleted to 0
        // save
    }
}
