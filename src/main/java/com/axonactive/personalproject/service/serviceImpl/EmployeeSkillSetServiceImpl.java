package com.axonactive.personalproject.service.serviceImpl;

import com.axonactive.personalproject.entity.EmployeeSkillSet;
import com.axonactive.personalproject.repository.EmployeeSkillSetRepository;
import com.axonactive.personalproject.service.EmployeeSkillSetService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeSkillSetServiceImpl implements EmployeeSkillSetService {

    @Autowired
    EmployeeSkillSetRepository employeeSkillSetRepository;

    @Override
    public List<EmployeeSkillSet> findAll() {
        return employeeSkillSetRepository.findAll();
    }

    @Override
    public Optional<EmployeeSkillSet> findById(Integer id) {
        return employeeSkillSetRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        employeeSkillSetRepository.deleteById(id);
    }

    @Override
    public EmployeeSkillSet saveSkillSetList(EmployeeSkillSet employeeSkillSet) {
        return employeeSkillSetRepository.save(employeeSkillSet);
    }
}
