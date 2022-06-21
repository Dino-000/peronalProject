package com.axonactive.personalproject.service;

import com.axonactive.personalproject.controller.request.CandidateCertificationRequest;
import com.axonactive.personalproject.entity.CandidateCertification;
import com.axonactive.personalproject.exception.EntityNotFoundException;
import com.axonactive.personalproject.service.dto.CandidateCertificationDto;

import java.time.LocalDate;
import java.util.List;

public interface CandidateCertificationService {
  List<CandidateCertificationDto> findAll();

  CandidateCertificationDto findById(Integer id) throws EntityNotFoundException;

  List<CandidateCertificationDto> findByCandidateId(Integer Id);

  CandidateCertification add(CandidateCertificationRequest request) throws EntityNotFoundException;

  void deleteById(Integer id) throws EntityNotFoundException;

  CandidateCertificationDto update(CandidateCertificationRequest request, Integer id)
      throws EntityNotFoundException;

  CandidateCertification convertRequestToEntity(CandidateCertificationRequest request)
      throws EntityNotFoundException;

  Boolean isValidIssuedDate(LocalDate issuedDate);

  Boolean isValidExpiredDate(LocalDate issuedDate, LocalDate expiredDate);
}
