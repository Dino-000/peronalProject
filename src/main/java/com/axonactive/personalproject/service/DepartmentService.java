package com.axonactive.personalproject.service;

import com.axonactive.personalproject.entity.Department;
import com.axonactive.personalproject.exception.ResourceNotFoundException;

import java.util.List;

public interface DepartmentService {
  List<Department> findAll();

  Department findById(Integer id) throws ResourceNotFoundException;

  void deleteById(Integer id) throws ResourceNotFoundException;

  Department saveDepartment(Department input);

  Department update(Department input, Integer id) throws ResourceNotFoundException;
}
