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
  public static final String PATH = "api/departments";
  @Autowired DepartmentService departmentService;

  @GetMapping
  public ResponseEntity<List<Department>> getAll() {
    return ResponseEntity.ok().body(departmentService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Department> getById(@PathVariable("id") Integer id)
      throws ResourceNotFoundException {
    return ResponseEntity.created(URI.create(PATH + "/" + id)).body(departmentService.findById(id));
  }

  @PostMapping
  public ResponseEntity<Department> add(@RequestBody Department inputData) {
    Department newDepartment = departmentService.saveDepartment(inputData);

    return ResponseEntity.created(URI.create(PATH + "/" + newDepartment.getId()))
        .body(newDepartment);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Department> update(
      @PathVariable("id") Integer id, @RequestBody Department inputData)
      throws ResourceNotFoundException {

    return ResponseEntity.created(URI.create(PATH + "/" + id))
        .body(departmentService.update(inputData, id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Integer id)
      throws ResourceNotFoundException {
    departmentService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
