package com.axonactive.personalproject.service;

import com.axonactive.personalproject.entity.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    List<Department> findAll();
    Optional<Department> findById(Integer id);

    void deleteById(Integer id);

    Department saveDepartment(Department department);
}