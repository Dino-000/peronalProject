package com.axonactive.personalproject.service.serviceImpl;

import com.axonactive.personalproject.entity.CandidateCertification;
import com.axonactive.personalproject.repository.CandidateCertificationRepository;
import com.axonactive.personalproject.service.CandidateCertificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CandidateCertificationServiceImpl implements CandidateCertificationService {
  @Autowired
  CandidateCertificationRepository candidateCertificationRepository;

  @Override
  public List<CandidateCertification> findAll() {
    return candidateCertificationRepository.findAll();
  }

  @Override
  public Optional<CandidateCertification> findById(Integer id) {
    return candidateCertificationRepository.findById(id);
  }

  @Override
  public List<CandidateCertification> findByCandidateId(Integer id) {
    return candidateCertificationRepository.findByCandidateId(id);
  }

  @Override
  public void deleteById(Integer id) {
    candidateCertificationRepository.deleteById(id);
  }

  @Override
  public CandidateCertification saveCertificationList(CandidateCertification candidateCertification) {
    return candidateCertificationRepository.save(candidateCertification);
  }
}
