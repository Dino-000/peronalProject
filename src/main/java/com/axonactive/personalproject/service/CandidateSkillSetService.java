package com.axonactive.personalproject.service;

import com.axonactive.personalproject.controller.request.CandidateSkillSetRequest;
import com.axonactive.personalproject.entity.CandidateSkillSet;
import com.axonactive.personalproject.exception.ResourceNotFoundException;
import com.axonactive.personalproject.service.dto.CandidateSkillSetDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateSkillSetService {
    List<CandidateSkillSetDto> findAll();
    CandidateSkillSetDto findById(Integer id) throws ResourceNotFoundException;
    List<CandidateSkillSetDto> findByCandidateId(Integer id);

    CandidateSkillSet convertRequestToEntity (CandidateSkillSetRequest request) throws ResourceNotFoundException;
    void deleteById(Integer id) throws ResourceNotFoundException;
    CandidateSkillSetDto update (CandidateSkillSetRequest request, Integer id) throws ResourceNotFoundException;
    CandidateSkillSet add(CandidateSkillSetRequest request) throws ResourceNotFoundException;
}
