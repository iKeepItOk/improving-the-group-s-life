package org.example.firsttry.repository;

import org.example.firsttry.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    Student findBySurname(String firstName);

    @Transactional
    void deleteBySurname(String firstName);
}
