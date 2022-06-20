package com.axonactive.personalproject.service.serviceImpl;

import com.axonactive.personalproject.entity.Department;
import com.axonactive.personalproject.exception.EntityNotFoundException;
import com.axonactive.personalproject.repository.DepartmentRepository;
import com.axonactive.personalproject.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
  @Autowired DepartmentRepository departmentRepository;

  @Override
  public List<Department> findAll() {
    return departmentRepository.findAll();
  }

  @Override
  public Department findById(Integer id) throws EntityNotFoundException {
    return departmentRepository
        .findById(id)
        .orElseThrow(
                EntityNotFoundException::departmentNotFound);
  }

  @Override
  public void deleteById(Integer id) throws EntityNotFoundException {
    findById(id);
    departmentRepository.deleteById(id);
  }

  @Override
  public Department saveDepartment(Department input) {
    return departmentRepository.save(
        new Department(
            null,
            input.getName(),
            input.getHeadcount(),
            input.getQuantityOfHiringManager(),
            input.getManagerID()));
  }

  @Override
  public Department update(Department input, Integer id) throws EntityNotFoundException {
    Department updatingDepartment =
        departmentRepository
            .findById(id)
            .orElseThrow(
                    EntityNotFoundException::departmentNotFound);
    updatingDepartment.setName(input.getName());
    updatingDepartment.setHeadcount(input.getHeadcount());
    updatingDepartment.setQuantityOfHiringManager(input.getQuantityOfHiringManager());
    updatingDepartment.setManagerID(input.getManagerID());
    return departmentRepository.save(updatingDepartment);
  }
}
