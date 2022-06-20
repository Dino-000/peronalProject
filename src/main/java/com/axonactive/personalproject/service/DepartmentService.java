package com.axonactive.personalproject.service;

import com.axonactive.personalproject.entity.Department;
import com.axonactive.personalproject.exception.EntityNotFoundException;

import java.util.List;

public interface DepartmentService {
  List<Department> findAll();

  Department findById(Integer id) throws EntityNotFoundException;

  void deleteById(Integer id) throws EntityNotFoundException;

  Department saveDepartment(Department input);

  Department update(Department input, Integer id) throws EntityNotFoundException;
}
