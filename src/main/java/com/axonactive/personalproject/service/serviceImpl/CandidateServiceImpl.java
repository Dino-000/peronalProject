package com.axonactive.personalproject.service.serviceImpl;

import com.axonactive.personalproject.entity.Candidate;
import com.axonactive.personalproject.repository.CandidateRepository;
import com.axonactive.personalproject.service.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {
@Autowired
    CandidateRepository candidateRepository;

    @Override
    public List<Candidate> findAll() {
        return candidateRepository.findAll();
    }

    @Override
    public Optional<Candidate> findById(Integer id) {
        return candidateRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
    candidateRepository.deleteById(id);
    }

    @Override
    public Candidate saveCandidate(Candidate candidate) {
        return candidateRepository.save(candidate);
    }
}
