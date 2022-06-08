package com.axonactive.personalproject.service;

import com.axonactive.personalproject.entity.EmployeeEducation;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeEducationService {
    List<EmployeeEducation> findAll();
    Optional<EmployeeEducation> findById(Integer id);

    void deleteById(Integer id);

    EmployeeEducation saveEducationList(EmployeeEducation employeeEducation);
}
