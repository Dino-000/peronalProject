package com.axonactive.personalproject.service;

import com.axonactive.personalproject.entity.EmployeeSkillSet;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeSkillSetService {
    List<EmployeeSkillSet> findAll();
    Optional<EmployeeSkillSet> findById(Integer id);

    void deleteById(Integer id);

    EmployeeSkillSet saveSkillSetList(EmployeeSkillSet employeeSkillSet);
}
