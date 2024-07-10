package org.example.firsttry.repository;

import org.example.firsttry.entity.UniversityGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface GroupRepository extends JpaRepository<UniversityGroup, Integer> {

    UniversityGroup findUniversityGroupByNumber(String number);

    @Transactional
    void deleteUniversityGroupByNumber(String number);
}
