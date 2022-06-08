package com.axonactive.personalproject.service.serviceImpl;

import com.axonactive.personalproject.entity.EmployeeCertification;
import com.axonactive.personalproject.repository.EmployeeCertificationRepository;
import com.axonactive.personalproject.service.EmployeeCertificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeCertificationServiceImpl implements EmployeeCertificationService {
  @Autowired
  EmployeeCertificationRepository employeeCertificationRepository;

  @Override
  public List<EmployeeCertification> findAll() {
    return employeeCertificationRepository.findAll();
  }

  @Override
  public Optional<EmployeeCertification> findById(Integer id) {
    return employeeCertificationRepository.findById(id);
  }

  @Override
  public void deleteById(Integer id) {
    employeeCertificationRepository.deleteById(id);
  }

  @Override
  public EmployeeCertification saveCertificationList(EmployeeCertification employeeCertification) {
    return employeeCertificationRepository.save(employeeCertification);
  }
}
