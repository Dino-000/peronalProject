package com.axonactive.personalproject.service;

import com.axonactive.personalproject.entity.Candidate;
import com.axonactive.personalproject.entity.SkillSet;

import java.util.List;
import java.util.Optional;

public interface SkillSetService {
    List<SkillSet> findAll();
    Optional<SkillSet> findById(Integer id);

    void deleteById(Integer id);

    SkillSet saveSkillSet(SkillSet skillSet);
}
