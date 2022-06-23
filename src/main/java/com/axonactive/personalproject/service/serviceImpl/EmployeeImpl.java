package com.axonactive.personalproject.service.serviceImpl;

import com.axonactive.personalproject.controller.request.EmployeeRequest;
import com.axonactive.personalproject.entity.Employee;
import com.axonactive.personalproject.exception.BusinessConstraintException;
import com.axonactive.personalproject.exception.EntityNotFoundException;
import com.axonactive.personalproject.repository.DepartmentRepository;
import com.axonactive.personalproject.repository.EmployeeRepository;
import com.axonactive.personalproject.service.EmployeeService;
import com.axonactive.personalproject.service.dto.EmployeeDto;
import com.axonactive.personalproject.service.mapper.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeImpl implements EmployeeService {
  @Autowired EmployeeRepository employeeRepository;
  @Autowired DepartmentRepository departmentRepository;

  @Override
  public List<EmployeeDto> findAll() {
    return EmployeeMapper.INSTANCE.toDtos(employeeRepository.findAll());
  }

  @Override
  public EmployeeDto findById(Integer id) {
    return EmployeeMapper.INSTANCE.toDto(
        employeeRepository.findById(id).orElseThrow(EntityNotFoundException::employeeNotFound));
  }

  @Override
  public void deleteById(Integer id) {
    findById(id);
    employeeRepository.deleteById(id);
  }

  @Override
  public EmployeeDto update(EmployeeRequest request, Integer id) {
    Employee updatingEmployee =
        employeeRepository.findById(id).orElseThrow(EntityNotFoundException::employeeNotFound);
    updatingEmployee.setEmployeeId(request.getEmployeeId());
    updatingEmployee.setName(request.getName());
    updatingEmployee.setDateOfBirth(request.getDateOfBirth());
    updatingEmployee.setTeam(request.getTeam());
    updatingEmployee.setDepartment(
        departmentRepository
            .findById(request.getDepartmentId())
            .orElseThrow(EntityNotFoundException::employeeNotFound));
    return EmployeeMapper.INSTANCE.toDto(employeeRepository.save(updatingEmployee));
  }

  @Override
  public Employee convertFromRequestToEntity(EmployeeRequest request) {

    return new Employee(
        null,
        request.getEmployeeId(),
        request.getName(),
        request.getDateOfBirth(),
        request.getTeam(),
        departmentRepository
            .findById(request.getDepartmentId())
            .orElseThrow(EntityNotFoundException::employeeNotFound));
  }

  @Override
  public Employee add(EmployeeRequest request) {
    return employeeRepository.save(convertFromRequestToEntity(request));
  }

  public Employee checkValidHrOfficerId(Integer employeeId) {
    Employee hrOfficer =
        employeeRepository
            .findById(employeeId)
            .orElseThrow(EntityNotFoundException::employeeNotFound);
    if (isValidHrOfficer(hrOfficer)) {
      return hrOfficer;
    } else {
      throw BusinessConstraintException.invalidHrOfficer();
    }
  }

  @Override
  public Employee checkValidHiringManagerId(Integer employeeId) {
    Employee hiringManager =
        employeeRepository
            .findById(employeeId)
            .orElseThrow(EntityNotFoundException::employeeNotFound);
    if (isValidHiringManager(hiringManager)) {
      return hiringManager;
    } else {
      throw BusinessConstraintException.invalidHiringManagerOfficer();
    }
  }

  public Boolean isValidHrOfficer(Employee employee) {
    return employee.getDepartment().getName().equalsIgnoreCase("hr");
  }

  @Override
  public Boolean isValidHiringManager(Employee employee) {
    return !employee.getDepartment().getName().equalsIgnoreCase("hr")
        || employee.getDepartment().getName().equalsIgnoreCase("hr")
            && employee.getEmployeeId().equalsIgnoreCase(employee.getDepartment().getManagerID());
  }
}
