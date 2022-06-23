package com.axonactive.personalproject.service;

import com.axonactive.personalproject.entity.Department;

import java.util.List;

public interface DepartmentService {
  List<Department> findAll();

  Department findById(Integer id);

  void deleteById(Integer id);

  Department add(Department input);

  Department update(Department input, Integer id);
}
