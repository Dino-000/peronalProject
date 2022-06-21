package com.axonactive.personalproject.service;

import com.axonactive.personalproject.entity.Certification;
import com.axonactive.personalproject.exception.EntityNotFoundException;

import java.util.List;

public interface CertificationService {
  List<Certification> findAll();

  Certification findById(Integer id) throws EntityNotFoundException;

  void deleteById(Integer id) throws EntityNotFoundException;

  Certification saveCertification(Certification certification);

  List<Certification> findByCandidateId(Integer id);

  Certification update(Certification request, Integer id) throws EntityNotFoundException;

  Certification add(Certification request);
}
