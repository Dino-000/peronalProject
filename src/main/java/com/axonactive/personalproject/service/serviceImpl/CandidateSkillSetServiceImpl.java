package com.axonactive.personalproject.service.serviceImpl;

import com.axonactive.personalproject.entity.CandidateSkillSet;
import com.axonactive.personalproject.repository.CandidateSkillSetRepository;
import com.axonactive.personalproject.service.CandidateSkillSetService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CandidateSkillSetServiceImpl implements CandidateSkillSetService {

    @Autowired
    CandidateSkillSetRepository candidateSkillSetRepository;

    @Override
    public List<CandidateSkillSet> findAll() {
        return candidateSkillSetRepository.findAll();
    }

    @Override
    public Optional<CandidateSkillSet> findById(Integer id) {
        return candidateSkillSetRepository.findById(id);
    }

    @Override
    public List<CandidateSkillSet> findByCandidateId(Integer id) {
        return candidateSkillSetRepository.findByCandidateId(id);
    }

    @Override
    public void deleteById(Integer id) {
        candidateSkillSetRepository.deleteById(id);
    }

    @Override
    public CandidateSkillSet saveSkillSetList(CandidateSkillSet candidateSkillSet) {
        return candidateSkillSetRepository.save(candidateSkillSet);
    }
}
