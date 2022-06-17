package com.axonactive.personalproject.service.serviceImpl;

import com.axonactive.personalproject.controller.request.EmployeeRequest;
import com.axonactive.personalproject.entity.Employee;
import com.axonactive.personalproject.exception.ResourceNotFoundException;
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
  public EmployeeDto findById(Integer id) throws ResourceNotFoundException {
    return EmployeeMapper.INSTANCE.toDto(
        employeeRepository
            .findById(id)
            .orElseThrow(
                () -> new ResourceNotFoundException("Can't not find Employee with that id.")));
  }

  @Override
  public void deleteById(Integer id) throws ResourceNotFoundException {
    findById(id);
    employeeRepository.deleteById(id);
  }

  @Override
  public EmployeeDto update(EmployeeRequest request, Integer id) throws ResourceNotFoundException {
    Employee updatingEmployee =
        employeeRepository
            .findById(id)
            .orElseThrow(
                () -> new ResourceNotFoundException("Can't not find Employee with that id."));
    updatingEmployee.setEmployeeId(request.getEmployeeId());
    updatingEmployee.setName(request.getName());
    updatingEmployee.setDateOfBirth(request.getDateOfBirth());
    updatingEmployee.setTeam(request.getTeam());
    updatingEmployee.setDepartment(
        departmentRepository
            .findById(request.getDepartmentId())
            .orElseThrow(
                () -> new ResourceNotFoundException("Can't not find Department with that id.")));
    return EmployeeMapper.INSTANCE.toDto(employeeRepository.save(updatingEmployee));
  }

  @Override
  public Employee convertFromRequestToEntity(EmployeeRequest request)
      throws ResourceNotFoundException {
    return new Employee(
        null,
        request.getEmployeeId(),
        request.getName(),
        request.getDateOfBirth(),
        request.getTeam(),
        departmentRepository
            .findById(request.getDepartmentId())
            .orElseThrow(
                () -> new ResourceNotFoundException("Can't not find Department with that id.")));
  }

  @Override
  public Employee add(EmployeeRequest request) throws ResourceNotFoundException {
    return employeeRepository.save(convertFromRequestToEntity(request));
  }
}
