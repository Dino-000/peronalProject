package com.axonactive.personalproject.controller;

import com.axonactive.personalproject.controller.request.EmployeeRequest;
import com.axonactive.personalproject.entity.Employee;
import com.axonactive.personalproject.exception.ResourceNotFoundException;
import com.axonactive.personalproject.service.DepartmentService;
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
    public static final String PATH ="api/employees";
    @Autowired
    EmployeeService employeeService;
    @Autowired
    DepartmentService departmentService;


    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAll() {
        return ResponseEntity.ok().body(EmployeeMapper.INSTANCE.toDtos(employeeService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getById (@PathVariable("id") Integer id) throws ResourceNotFoundException {
        Employee employee = employeeService.findById(id).orElseThrow(()->new ResourceNotFoundException("Can't not find Employee with that id."));
        return ResponseEntity.created(URI.create(PATH+"/"+employee.getId())).body(EmployeeMapper.INSTANCE.toDto(employee));
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> add(
            @RequestBody EmployeeRequest inputData) {
        Employee newEmployee = employeeService.saveEmployee(new Employee(null,
                inputData.getEmployeeId(),
                inputData.getName(),
                inputData.getDateOfBirth(),
                inputData.getTeam(),
                departmentService.findById(inputData.getDepartmentId()).get()
        ));

        return ResponseEntity.created(URI.create(PATH + "/" + newEmployee.getId())).body(EmployeeMapper.INSTANCE.toDto(newEmployee));
    }

    @PutMapping("/{id}")
    public  ResponseEntity<EmployeeDto> update(@PathVariable("id") Integer id, @RequestBody EmployeeRequest inputData) throws ResourceNotFoundException {
        Employee updatingEmployee = employeeService.findById(id).orElseThrow(()->new ResourceNotFoundException("Can't not find Employee with that id."));
        updatingEmployee.setEmployeeId(inputData.getEmployeeId());
        updatingEmployee.setName(inputData.getName());
        updatingEmployee.setDateOfBirth(inputData.getDateOfBirth());
        updatingEmployee.setTeam(inputData.getTeam());
        updatingEmployee.setDepartment(departmentService.findById(inputData.getDepartmentId()).get());
        Employee updatedEmployee = employeeService.saveEmployee(updatingEmployee);
        return  ResponseEntity.created(URI.create(PATH+"/"+id)).body(EmployeeMapper.INSTANCE.toDto(updatedEmployee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        Employee deletingEmployee = employeeService.findById(id).orElseThrow(()->new ResourceNotFoundException("Can't not find Employee with that id."));
        employeeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
