package org.example.firsttry.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "university_groups")
public class UniversityGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private int id;

    @Column(name = "group_number", unique = true, nullable = false)
    private String number;

    @OneToMany(mappedBy = "group", cascade = {CascadeType.REFRESH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.DETACH}, fetch = FetchType.EAGER)
    private List<Student> students;

    public void addStudentToUniversityGroup(Student student) {
        if (students == null) {
            students = new ArrayList<>();
        }
        students.add(student);
        log.info("Added student to university group, student surname {}", student.getSurname());
    }

    public void deleteStudentFromUniversityGroup(Student student) {
        students.remove(student);
        log.info("delete student to university group, student surname {}", student.getSurname());
    }
}
