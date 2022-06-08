package com.axonactive.personalproject.service;

import com.axonactive.personalproject.entity.EmployeeCertification;

import java.util.List;
import java.util.Optional;

public interface EmployeeCertificationService {
    List<EmployeeCertification> findAll();
    Optional<EmployeeCertification> findById(Integer id);

    void deleteById(Integer id);

    EmployeeCertification saveCertificationList(EmployeeCertification employeeCertification);
}
