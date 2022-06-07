package com.axonactive.personalproject.serviceImpl;

import com.axonactive.personalproject.repository.DepartmentRepository;
import com.axonactive.personalproject.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
@Autowired
    DepartmentRepository departmentRepository;
}