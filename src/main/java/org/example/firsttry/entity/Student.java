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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "surname")
    private String surname;

    @ManyToOne(cascade = {CascadeType.REFRESH,CascadeType.MERGE,
            CascadeType.PERSIST,CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "university_groups_id")
    private UniversityGroup group;
}
