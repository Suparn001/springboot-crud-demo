package com.gautam.crudSpringBootDemo.repository;


import com.gautam.crudSpringBootDemo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository(not required as this is interface)
public interface StudentRepository extends JpaRepository<Student, Long> {


}
