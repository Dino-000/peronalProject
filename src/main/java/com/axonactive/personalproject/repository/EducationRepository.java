package com.axonactive.personalproject.repository;

import com.axonactive.personalproject.entity.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface EducationRepository extends JpaRepository<Education,Integer> {

}
