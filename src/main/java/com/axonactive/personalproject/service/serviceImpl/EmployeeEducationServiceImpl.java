package com.axonactive.personalproject.service.serviceImpl;

import com.axonactive.personalproject.entity.EmployeeEducation;
import com.axonactive.personalproject.repository.EmployeeEducationRepository;
import com.axonactive.personalproject.service.EmployeeEducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeEducationServiceImpl implements EmployeeEducationService {
@Autowired
EmployeeEducationRepository employeeEducationRepository;
    @Override
    public List<EmployeeEducation> findAll() {
        return employeeEducationRepository.findAll();
    }

    @Override
    public Optional<EmployeeEducation> findById(Integer id) {
        return employeeEducationRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
employeeEducationRepository.deleteById(id);
    }

    @Override
    public EmployeeEducation saveEducationList(EmployeeEducation employeeEducation) {
       return employeeEducationRepository.save(employeeEducation);
    }
}
