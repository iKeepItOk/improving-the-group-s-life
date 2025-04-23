package org.example.firsttry.repository;

import org.example.firsttry.entity.UniversityGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<UniversityGroup, Integer> {

    UniversityGroup findUniversityGroupByNumber(String number);


    void deleteUniversityGroupByNumber(String number);
}
