package com.axonactive.personalproject.service;

import com.axonactive.personalproject.controller.request.HiringRequestSkillSetRequest;
import com.axonactive.personalproject.entity.HiringRequestSkillSet;
import com.axonactive.personalproject.exception.ResourceNotFoundException;
import com.axonactive.personalproject.service.dto.HiringRequestSkillSetDto;

import java.util.List;

public interface HiringRequestSkillSetService {
    List<HiringRequestSkillSetDto> findAll();
    HiringRequestSkillSetDto findById(Integer id) throws ResourceNotFoundException;

    void deleteById(Integer id) throws ResourceNotFoundException;

    HiringRequestSkillSet add(HiringRequestSkillSetRequest request) throws ResourceNotFoundException;
    HiringRequestSkillSetDto update (HiringRequestSkillSetRequest request, Integer id) throws ResourceNotFoundException;
    HiringRequestSkillSet convertRequestToEntity (HiringRequestSkillSetRequest request) throws ResourceNotFoundException;
}
