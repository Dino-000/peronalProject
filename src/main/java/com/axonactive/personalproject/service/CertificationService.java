package com.axonactive.personalproject.service;

import com.axonactive.personalproject.entity.Certification;

import java.util.List;

public interface CertificationService {
  List<Certification> findAll();

  Certification findById(Integer id);

  void deleteById(Integer id);

  Certification add(Certification certification);

  List<Certification> findByCandidateId(Integer id);

  Certification update(Certification request, Integer id);
}
