package org.example.firsttry.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private int id;

    @Column(name = "surname", nullable = false)
    private String surname;

    @ManyToOne(cascade = {CascadeType.REFRESH,CascadeType.MERGE,
            CascadeType.PERSIST,CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "university_groups_id")
    private UniversityGroup group;
}
