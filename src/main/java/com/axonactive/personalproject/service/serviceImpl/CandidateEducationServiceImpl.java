package com.axonactive.personalproject.service.serviceImpl;

import com.axonactive.personalproject.entity.CandidateEducation;
import com.axonactive.personalproject.repository.CandidateEducationRepository;
import com.axonactive.personalproject.service.CandidateEducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CandidateEducationServiceImpl implements CandidateEducationService {
@Autowired
CandidateEducationRepository candidateEducationRepository;
    @Override
    public List<CandidateEducation> findAll() {
        return candidateEducationRepository.findAll();
    }

    @Override
    public Optional<CandidateEducation> findById(Integer id) {
        return candidateEducationRepository.findById(id);
    }

    @Override
    public List<CandidateEducation> findByCandidateId(Integer id) {
        return candidateEducationRepository.findByCandidateId(id);
    }

    @Override
    public void deleteById(Integer id) {
candidateEducationRepository.deleteById(id);
    }

    @Override
    public CandidateEducation saveEducationList(CandidateEducation candidateEducation) {
       return candidateEducationRepository.save(candidateEducation);
    }
}
