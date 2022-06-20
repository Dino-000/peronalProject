package com.axonactive.personalproject.controller;

import com.axonactive.personalproject.controller.request.EmployeeRequest;
import com.axonactive.personalproject.entity.Employee;
import com.axonactive.personalproject.exception.EntityNotFoundException;
import com.axonactive.personalproject.service.EmployeeService;
import com.axonactive.personalproject.service.dto.EmployeeDto;
import com.axonactive.personalproject.service.mapper.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = EmployeeResource.PATH)
@RequiredArgsConstructor
public class EmployeeResource {
  public static final String PATH = "api/employees";
  @Autowired EmployeeService employeeService;

  @GetMapping
  public ResponseEntity<List<EmployeeDto>> getAll() {
    return ResponseEntity.ok().body(employeeService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<EmployeeDto> getById(
      @PathVariable("id") Integer id,
      @RequestParam(value = "phoneNumber", required = false) String phoneNumber)
      throws EntityNotFoundException {

    return ResponseEntity.created(URI.create(PATH + "/" + id)).body(employeeService.findById(id));
  }

  @PostMapping
  public ResponseEntity<EmployeeDto> add(@RequestBody EmployeeRequest inputData)
      throws EntityNotFoundException {
    Employee newEmployee = employeeService.add(inputData);

    return ResponseEntity.created(URI.create(PATH + "/" + newEmployee.getId()))
        .body(EmployeeMapper.INSTANCE.toDto(newEmployee));
  }

  @PutMapping("/{id}")
  public ResponseEntity<EmployeeDto> update(
      @PathVariable("id") Integer id, @RequestBody EmployeeRequest inputData)
      throws EntityNotFoundException {

    return ResponseEntity.created(URI.create(PATH + "/" + id))
        .body(employeeService.update(inputData, id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Integer id)
      throws EntityNotFoundException {

    employeeService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
