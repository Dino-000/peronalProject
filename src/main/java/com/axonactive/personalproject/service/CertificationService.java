package com.axonactive.personalproject.service;

import com.axonactive.personalproject.entity.Certification;
import com.axonactive.personalproject.exception.ResourceNotFoundException;

import java.util.List;

public interface CertificationService {
    List<Certification> findAll();
    Certification findById(Integer id) throws ResourceNotFoundException;

    void deleteById(Integer id) throws ResourceNotFoundException;

    Certification saveCertification(Certification certification);

    List<Certification> findByCandidateId(Integer id);
    Certification update(Certification request, Integer id) throws ResourceNotFoundException;
    Certification add (Certification request);
}
