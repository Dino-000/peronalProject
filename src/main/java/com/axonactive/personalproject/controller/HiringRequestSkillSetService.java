package com.axonactive.personalproject.controller;

import com.axonactive.personalproject.entity.HiringRequestSkillSet;

import java.util.List;
import java.util.Optional;

public interface HiringRequestSkillSetService {
    List<HiringRequestSkillSet> findAll();
    Optional<HiringRequestSkillSet> findById(Integer id);

    void deleteById(Integer id);

    HiringRequestSkillSet saveHiringRequestSkillSet(HiringRequestSkillSet hiringRequestSkillSet);
}
