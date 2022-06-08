package com.axonactive.personalproject.service;

import com.axonactive.personalproject.entity.CandidateCertification;

import java.util.List;
import java.util.Optional;

public interface CandidateCertificationService {
    List<CandidateCertification> findAll();
    Optional<CandidateCertification> findById(Integer id);

    void deleteById(Integer id);

    CandidateCertification saveCertificationList(CandidateCertification candidateCertification);
}
