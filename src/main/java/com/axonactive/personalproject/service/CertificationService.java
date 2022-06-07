package com.axonactive.personalproject.service;

import com.axonactive.personalproject.entity.Certification;

import java.util.List;
import java.util.Optional;

public interface CertificationService {
    List<Certification> findAll();
    Optional<Certification> findById(Integer id);

    void deleteById(Integer id);

    Certification saveCertification(Certification certification);
}
