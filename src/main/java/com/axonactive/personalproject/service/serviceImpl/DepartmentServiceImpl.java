package com.axonactive.personalproject.service.serviceImpl;

import com.axonactive.personalproject.entity.Department;
import com.axonactive.personalproject.repository.DepartmentRepository;
import com.axonactive.personalproject.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
@Autowired
    DepartmentRepository departmentRepository;

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Optional<Department> findById(Integer id) {
        return departmentRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }
}