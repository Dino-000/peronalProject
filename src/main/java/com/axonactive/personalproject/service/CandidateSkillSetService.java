package com.axonactive.personalproject.service;

import com.axonactive.personalproject.entity.CandidateSkillSet;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateSkillSetService {
    List<CandidateSkillSet> findAll();
    Optional<CandidateSkillSet> findById(Integer id);

    void deleteById(Integer id);

    CandidateSkillSet saveSkillSetList(CandidateSkillSet candidateSkillSet);
}
