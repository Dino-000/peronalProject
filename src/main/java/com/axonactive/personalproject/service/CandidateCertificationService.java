package com.axonactive.personalproject.service;

import com.axonactive.personalproject.controller.request.CandidateCertificationRequest;
import com.axonactive.personalproject.entity.CandidateCertification;
import com.axonactive.personalproject.service.dto.CandidateCertificationDto;

import java.time.LocalDate;
import java.util.List;

public interface CandidateCertificationService {
  List<CandidateCertificationDto> findAll();

  CandidateCertificationDto findById(Integer id);

  List<CandidateCertificationDto> findByCandidateId(Integer Id);

  CandidateCertification add(CandidateCertificationRequest request);

  void deleteById(Integer id);

  CandidateCertificationDto update(CandidateCertificationRequest request, Integer id);

  CandidateCertification convertRequestToEntity(CandidateCertificationRequest request);

  Boolean isValidIssuedDate(LocalDate issuedDate);

  Boolean isValidExpiredDate(LocalDate issuedDate, LocalDate expiredDate);

  LocalDate checkValidIssuedDate(LocalDate issuedDate);

  LocalDate checkValidExpiredDate(LocalDate issuedDate, LocalDate expiredDate);
}
