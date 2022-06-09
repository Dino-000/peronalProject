package com.axonactive.personalproject.controller;

import com.axonactive.personalproject.entity.Department;
import com.axonactive.personalproject.exception.ResourceNotFoundException;
import com.axonactive.personalproject.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
@RestController
@RequestMapping(path = DepartmentResource.PATH)
@RequiredArgsConstructor
public class DepartmentResource {
    public static final String PATH ="api/Departments";
    @Autowired
    DepartmentService departmentService;


    @GetMapping
    public ResponseEntity<List<Department>> getAll() {
        return ResponseEntity.ok().body(departmentService.findAll());
    }

    @PostMapping
    public ResponseEntity<Department> add(
            @RequestBody Department inputData) {
        Department newDepartment = departmentService.saveDepartment(new Department(null,
                inputData.getName(),
                inputData.getHeadcount(),
                inputData.getQuantityOfHiringManager(),
                inputData.getManagerID()
        ));

        return ResponseEntity.created(URI.create(PATH + "/" + newDepartment.getId())).body(newDepartment);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<Department> update(@PathVariable("id") Integer id, @RequestBody Department inputData) throws ResourceNotFoundException {
        Department updatingDepartment = departmentService.findById(id).orElseThrow(()->new ResourceNotFoundException("Can't not find Application Form with that id."));
        updatingDepartment.setName(inputData.getName());
        updatingDepartment.setHeadcount(inputData.getHeadcount());
        updatingDepartment.setQuantityOfHiringManager(inputData.getQuantityOfHiringManager());
        updatingDepartment.setManagerID(inputData.getManagerID());
        Department updatedDepartment = departmentService.saveDepartment(updatingDepartment);
        return  ResponseEntity.created(URI.create(PATH+"/"+id)).body(updatedDepartment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        Department deletingDepartment = departmentService.findById(id).orElseThrow(()->new ResourceNotFoundException("Can't not find Application Form with that id."));
        departmentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
