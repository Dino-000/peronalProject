package com.axonactive.personalproject.service;

import com.axonactive.personalproject.controller.request.EmployeeRequest;
import com.axonactive.personalproject.entity.Employee;
import com.axonactive.personalproject.exception.ResourceNotFoundException;
import com.axonactive.personalproject.service.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDto> findAll();
    EmployeeDto findById(Integer id) throws ResourceNotFoundException;

    void deleteById(Integer id) throws ResourceNotFoundException;
    EmployeeDto update (EmployeeRequest request, Integer id) throws ResourceNotFoundException;

    Employee convertFromRequestToEntity (EmployeeRequest request) throws ResourceNotFoundException;
    Employee add(EmployeeRequest request) throws ResourceNotFoundException;
}
