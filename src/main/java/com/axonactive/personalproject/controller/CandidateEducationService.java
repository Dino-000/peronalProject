package com.axonactive.personalproject.controller;

import com.axonactive.personalproject.entity.CandidateEducation;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateEducationService {
    List<CandidateEducation> findAll();
    Optional<CandidateEducation> findById(Integer id);

    void deleteById(Integer id);

    CandidateEducation saveEducationList(CandidateEducation candidateEducation);
}
