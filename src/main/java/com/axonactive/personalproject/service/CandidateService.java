package com.axonactive.personalproject.service;

import com.axonactive.personalproject.entity.ApplicationForm;
import com.axonactive.personalproject.entity.Candidate;

import java.util.List;
import java.util.Optional;

public interface CandidateService {
    List<Candidate> findAll();
    Optional<Candidate> findById(Integer id);

    void deleteById(Integer id);

    Candidate saveCandidate(Candidate candidate);
}
