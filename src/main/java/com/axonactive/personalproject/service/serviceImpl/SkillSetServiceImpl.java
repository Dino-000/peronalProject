package com.axonactive.personalproject.service.serviceImpl;

import com.axonactive.personalproject.entity.SkillSet;
import com.axonactive.personalproject.exception.EntityNotFoundException;
import com.axonactive.personalproject.repository.SkillSetRepository;
import com.axonactive.personalproject.service.SkillSetService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillSetServiceImpl implements SkillSetService {
  @Autowired SkillSetRepository skillSetRepository;

  @Override
  public List<SkillSet> findAll() {
    return skillSetRepository.findAll();
  }

  @Override
  public SkillSet findById(Integer id) throws EntityNotFoundException {
    return skillSetRepository.findById(id).orElseThrow(EntityNotFoundException::skillSetNotFound);
  }

  @Override
  public void deleteById(Integer id) throws EntityNotFoundException {
    findById(id);
    skillSetRepository.deleteById(id);
  }

  @Override
  public SkillSet add(SkillSet skillSet) {
    return skillSetRepository.save(skillSet);
  }

  @Override
  public List<SkillSet> findByCandidateId(Integer id) {
    return skillSetRepository.findByCandidateId(id);
  }

  @Override
  public SkillSet update(SkillSet skillSet, Integer id) throws EntityNotFoundException {
    SkillSet updatingSkillSet = findById(id);
    updatingSkillSet.setIndustryCategory(skillSet.getIndustryCategory());
    updatingSkillSet.setName(skillSet.getName());
    updatingSkillSet.setType(skillSet.getType());
    updatingSkillSet.setLevel(skillSet.getLevel());
    return updatingSkillSet;
  }
}
