package com.axonactive.personalproject.repository;

import com.axonactive.personalproject.entity.EmployeeSkillSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface EmployeeSkillSetRepository extends JpaRepository<EmployeeSkillSet,Integer> {

}
