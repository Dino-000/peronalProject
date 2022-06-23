package com.axonactive.personalproject.service;

import com.axonactive.personalproject.entity.SkillSet;

import java.util.List;

public interface SkillSetService {
  List<SkillSet> findAll();

  SkillSet findById(Integer id);

  void deleteById(Integer id);

  SkillSet add(SkillSet skillSet);

  List<SkillSet> findByCandidateId(Integer id);

  SkillSet update(SkillSet skillSet, Integer id);
}
