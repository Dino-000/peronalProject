package com.axonactive.personalproject.service;

import com.axonactive.personalproject.controller.request.CandidateEducationRequest;
import com.axonactive.personalproject.entity.CandidateEducation;
import com.axonactive.personalproject.exception.ResourceNotFoundException;
import com.axonactive.personalproject.service.dto.CandidateEducationDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateEducationService {
    List<CandidateEducationDto> findAll();
    CandidateEducationDto findById(Integer id) throws ResourceNotFoundException;
    List<CandidateEducationDto> findByCandidateId (Integer Id);

    CandidateEducation add(CandidateEducationRequest request) throws ResourceNotFoundException;

    CandidateEducationDto update (CandidateEducationRequest request, Integer id) throws ResourceNotFoundException;

    void deleteById(Integer id) throws ResourceNotFoundException;

    CandidateEducation convertFromRequestToEntity(CandidateEducationRequest request) throws ResourceNotFoundException;


}
