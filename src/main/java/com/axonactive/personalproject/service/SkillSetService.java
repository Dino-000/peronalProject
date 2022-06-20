package com.axonactive.personalproject.service;

import com.axonactive.personalproject.entity.SkillSet;
import com.axonactive.personalproject.exception.EntityNotFoundException;

import java.util.List;

public interface SkillSetService {
  List<SkillSet> findAll();

  SkillSet findById(Integer id) throws EntityNotFoundException;

  void deleteById(Integer id) throws EntityNotFoundException;

  SkillSet add(SkillSet skillSet);

  List<SkillSet> findByCandidateId(Integer id);

  SkillSet update(SkillSet skillSet, Integer id) throws EntityNotFoundException;
}
