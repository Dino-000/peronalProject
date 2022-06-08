package com.axonactive.personalproject.controller;

import com.axonactive.personalproject.controller.request.EmployeeRequest;
import com.axonactive.personalproject.entity.Employee;
import com.axonactive.personalproject.exception.ResourceNotFoundException;
import com.axonactive.personalproject.service.DepartmentService;
import com.axonactive.personalproject.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
@RestController
@RequestMapping(path = EmployeeController.PATH)
@RequiredArgsConstructor
public class EmployeeController {
    public static final String PATH ="api/Employees";
    @Autowired
    EmployeeService employeeService;
    @Autowired
    DepartmentService departmentService;


    @GetMapping
    public ResponseEntity<List<Employee>> getAll() {
        return ResponseEntity.ok().body(employeeService.findAll());
    }

    @PostMapping
    public ResponseEntity<Employee> add(
            @RequestBody EmployeeRequest inputData) {
        Employee newEmployee = employeeService.saveEmployee(new Employee(null,
                inputData.getEmployeeId(),
                inputData.getName(),
                inputData.getDateOfBirth(),
                inputData.getTeam(),
                departmentService.findById(inputData.getDepartmentId()).get()
        ));

        return ResponseEntity.created(URI.create(PATH + "/" + newEmployee.getId())).body(newEmployee);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<Employee> update(@PathVariable("id") Integer id, @RequestBody EmployeeRequest inputData) throws ResourceNotFoundException {
        Employee updatingEmployee = employeeService.findById(id).orElseThrow(()->new ResourceNotFoundException("Can't not find Application Form with that id."));
        updatingEmployee.setEmployeeId(inputData.getEmployeeId());
        updatingEmployee.setName(inputData.getName());
        updatingEmployee.setDateOfBirth(inputData.getDateOfBirth());
        updatingEmployee.setTeam(inputData.getTeam());
        updatingEmployee.setDepartment(departmentService.findById(inputData.getDepartmentId()).get());
        Employee updatedEmployee = employeeService.saveEmployee(updatingEmployee);
        return  ResponseEntity.created(URI.create(PATH+"/"+id)).body(updatedEmployee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        Employee deletingEmployee = employeeService.findById(id).orElseThrow(()->new ResourceNotFoundException("Can't not find Application Form with that id."));
        employeeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
