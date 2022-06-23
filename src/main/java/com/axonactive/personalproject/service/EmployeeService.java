package com.axonactive.personalproject.service;

import com.axonactive.personalproject.controller.request.EmployeeRequest;
import com.axonactive.personalproject.entity.Employee;
import com.axonactive.personalproject.service.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
  List<EmployeeDto> findAll();

  EmployeeDto findById(Integer id);

  void deleteById(Integer id);

  EmployeeDto update(EmployeeRequest request, Integer id);

  Employee convertFromRequestToEntity(EmployeeRequest request);

  Employee add(EmployeeRequest request);

  Employee checkValidHrOfficerId(Integer employeeId);

  Employee checkValidHiringManagerId(Integer employeeId);

  Boolean isValidHrOfficer(Employee employee);

  Boolean isValidHiringManager(Employee employee);
}
