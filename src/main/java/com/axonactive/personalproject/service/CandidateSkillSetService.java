package com.axonactive.personalproject.service;

import com.axonactive.personalproject.controller.request.CandidateSkillSetRequest;
import com.axonactive.personalproject.entity.CandidateSkillSet;
import com.axonactive.personalproject.service.dto.CandidateSkillSetDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateSkillSetService {
  List<CandidateSkillSetDto> findAll();

  CandidateSkillSetDto findById(Integer id);

  List<CandidateSkillSetDto> findByCandidateId(Integer id);

  CandidateSkillSet convertRequestToEntity(CandidateSkillSetRequest request);

  void deleteById(Integer id);

  CandidateSkillSetDto update(CandidateSkillSetRequest request, Integer id);

  CandidateSkillSet add(CandidateSkillSetRequest request);
}
