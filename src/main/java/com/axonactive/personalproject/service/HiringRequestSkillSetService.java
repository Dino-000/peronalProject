package com.axonactive.personalproject.service;

import com.axonactive.personalproject.controller.request.HiringRequestSkillSetRequest;
import com.axonactive.personalproject.entity.HiringRequestSkillSet;
import com.axonactive.personalproject.exception.EntityNotFoundException;
import com.axonactive.personalproject.service.dto.HiringRequestSkillSetDto;

import java.util.List;

public interface HiringRequestSkillSetService {
  List<HiringRequestSkillSetDto> findAll();

  HiringRequestSkillSetDto findById(Integer id) throws EntityNotFoundException;

  void deleteById(Integer id) throws EntityNotFoundException;

  HiringRequestSkillSet add(HiringRequestSkillSetRequest request) throws EntityNotFoundException;

  HiringRequestSkillSetDto update(HiringRequestSkillSetRequest request, Integer id)
      throws EntityNotFoundException;

  HiringRequestSkillSet convertRequestToEntity(HiringRequestSkillSetRequest request)
      throws EntityNotFoundException;
}
