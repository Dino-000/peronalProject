package com.axonactive.personalproject.service;

import com.axonactive.personalproject.controller.request.CandidateCertificationRequest;
import com.axonactive.personalproject.entity.CandidateCertification;
import com.axonactive.personalproject.exception.ResourceNotFoundException;
import com.axonactive.personalproject.service.dto.CandidateCertificationDto;

import java.util.List;

public interface CandidateCertificationService {
    List<CandidateCertificationDto> findAll();
    CandidateCertificationDto findById(Integer id) throws ResourceNotFoundException;
    List<CandidateCertificationDto> findByCandidateId (Integer Id);

    CandidateCertification add (CandidateCertificationRequest request) throws ResourceNotFoundException;


    void deleteById(Integer id) throws ResourceNotFoundException;

    CandidateCertificationDto update(CandidateCertificationRequest request,Integer id) throws ResourceNotFoundException;


    CandidateCertification convertRequestToEntity(CandidateCertificationRequest request) throws ResourceNotFoundException;
}
