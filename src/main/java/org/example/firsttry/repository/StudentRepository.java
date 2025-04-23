package org.example.firsttry.repository;

import org.example.firsttry.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    Student findBySurname(String firstName);

    void deleteBySurname(String firstName);
}
