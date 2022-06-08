package com.axonactive.personalproject.controller;

import com.axonactive.personalproject.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> findAll();
    Optional<Employee> findById(Integer id);

    void deleteById(Integer id);

    Employee saveEmployee(Employee employee);
}
