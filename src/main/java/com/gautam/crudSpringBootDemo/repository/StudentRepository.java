package com.gautam.crudSpringBootDemo.repository;


import com.gautam.crudSpringBootDemo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

//@Repository(not required as this is interface)
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByIdAndDeletedIsFalse(Long id);

    List<Student> findByDeletedIsFalse();
// findBy + fieldName + condition
}
