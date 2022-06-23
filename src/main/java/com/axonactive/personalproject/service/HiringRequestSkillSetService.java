package com.axonactive.personalproject.service;

import com.axonactive.personalproject.controller.request.HiringRequestSkillSetRequest;
import com.axonactive.personalproject.entity.HiringRequestSkillSet;
import com.axonactive.personalproject.service.dto.HiringRequestSkillSetDto;

import java.util.List;

public interface HiringRequestSkillSetService {
  List<HiringRequestSkillSetDto> findAll();

  HiringRequestSkillSetDto findById(Integer id);

  void deleteById(Integer id);

  HiringRequestSkillSet add(HiringRequestSkillSetRequest request);

  HiringRequestSkillSetDto update(HiringRequestSkillSetRequest request, Integer id);

  HiringRequestSkillSet convertRequestToEntity(HiringRequestSkillSetRequest request);
}
