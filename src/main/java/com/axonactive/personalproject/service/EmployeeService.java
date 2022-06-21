package com.axonactive.personalproject.service;

import com.axonactive.personalproject.controller.request.EmployeeRequest;
import com.axonactive.personalproject.entity.Employee;
import com.axonactive.personalproject.exception.EntityNotFoundException;
import com.axonactive.personalproject.service.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
  List<EmployeeDto> findAll();

  EmployeeDto findById(Integer id) throws EntityNotFoundException;

  void deleteById(Integer id) throws EntityNotFoundException;

  EmployeeDto update(EmployeeRequest request, Integer id) throws EntityNotFoundException;

  Employee convertFromRequestToEntity(EmployeeRequest request) throws EntityNotFoundException;

  Employee add(EmployeeRequest request) throws EntityNotFoundException;
}
