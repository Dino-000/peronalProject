package com.axonactive.personalproject.service;

import com.axonactive.personalproject.controller.request.CandidateEducationRequest;
import com.axonactive.personalproject.entity.CandidateEducation;
import com.axonactive.personalproject.service.dto.CandidateEducationDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateEducationService {
  List<CandidateEducationDto> findAll();

  CandidateEducationDto findById(Integer id);

  List<CandidateEducationDto> findByCandidateId(Integer Id);

  CandidateEducation add(CandidateEducationRequest request);

  CandidateEducationDto update(CandidateEducationRequest request, Integer id);

  void deleteById(Integer id);

  CandidateEducation convertFromRequestToEntity(CandidateEducationRequest request);
}
