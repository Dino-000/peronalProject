package com.axonactive.personalproject.serviceImpl;

import com.axonactive.personalproject.entity.HrOfficer;
import com.axonactive.personalproject.repository.HrOfficerRepository;
import com.axonactive.personalproject.service.HrOfficerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HrOfficerServiceImpl implements HrOfficerService {
  @Autowired HrOfficerRepository hrOfficerRepository;

  @Override
  public List<HrOfficer> findAll() {
    return hrOfficerRepository.findAll();
  }

  @Override
  public Optional<HrOfficer> findById(Integer id) {
    return hrOfficerRepository.findById(id);
  }

  @Override
  public void deleteById(Integer id) {
    hrOfficerRepository.deleteById(id);
  }

  @Override
  public HrOfficer saveHrOfficer(HrOfficer hrOfficer) {
    return hrOfficerRepository.save(hrOfficer);
  }
}
