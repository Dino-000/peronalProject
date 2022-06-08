package com.axonactive.personalproject.service.serviceImpl;

import com.axonactive.personalproject.entity.Certification;
import com.axonactive.personalproject.repository.CertificationRepository;
import com.axonactive.personalproject.service.CertificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CertificationServiceImpl implements CertificationService {
  @Autowired CertificationRepository certificationRepository;

  @Override
  public List<Certification> findAll() {
    return certificationRepository.findAll();
  }

  @Override
  public Optional<Certification> findById(Integer id) {
    return certificationRepository.findById(id);
  }

  @Override
  public void deleteById(Integer id) {
    certificationRepository.deleteById(id);
  }

  @Override
  public Certification saveCertification(Certification certification) {
    return certificationRepository.save(certification);
  }
}
