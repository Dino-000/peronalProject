package com.axonactive.personalproject.service.serviceImpl;

import com.axonactive.personalproject.controller.request.CandidateCertificationRequest;
import com.axonactive.personalproject.entity.CandidateCertification;
import com.axonactive.personalproject.exception.BusinessConstraintException;
import com.axonactive.personalproject.exception.EntityNotFoundException;
import com.axonactive.personalproject.repository.CandidateCertificationRepository;
import com.axonactive.personalproject.service.CandidateCertificationService;
import com.axonactive.personalproject.service.CandidateService;
import com.axonactive.personalproject.service.CertificationService;
import com.axonactive.personalproject.service.dto.CandidateCertificationDto;
import com.axonactive.personalproject.service.mapper.CandidateCertificationMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class CandidateCertificationServiceImpl implements CandidateCertificationService {
  @Autowired CandidateCertificationRepository candidateCertificationRepository;
  @Autowired CandidateService candidateService;
  @Autowired CertificationService certificationService;

  @Override
  public List<CandidateCertificationDto> findAll() {
    return CandidateCertificationMapper.INSTANCE.toDtos(candidateCertificationRepository.findAll());
  }

  @Override
  public CandidateCertificationDto findById(Integer id) {
    return CandidateCertificationMapper.INSTANCE.toDto(
        candidateCertificationRepository
            .findById(id)
            .orElseThrow(EntityNotFoundException::candidateCertificationNotFound));
  }

  @Override
  public List<CandidateCertificationDto> findByCandidateId(Integer id) {
    return CandidateCertificationMapper.INSTANCE.toDtos(
        candidateCertificationRepository.findByCandidateId(id));
  }

  @Override
  public CandidateCertification add(CandidateCertificationRequest request) {
    checkValidIssuedDate(request.getIssuedDate());
    checkValidExpiredDate(request.getIssuedDate(), request.getExpiredDate());
    return candidateCertificationRepository.save(convertRequestToEntity(request));
  }

  @Override
  public void deleteById(Integer id) {
    findById(id);
    candidateCertificationRepository.deleteById(id);
  }

  @Override
  public CandidateCertificationDto update(CandidateCertificationRequest request, Integer id) {
    CandidateCertification updatingCandidateCertification =
        candidateCertificationRepository
            .findById(id)
            .orElseThrow(EntityNotFoundException::candidateCertificationNotFound);
    updatingCandidateCertification.setCandidate(
        candidateService.findById(request.getCandidateId()));
    updatingCandidateCertification.setCertification(
        certificationService.findById(request.getCertificationId()));
    updatingCandidateCertification.setIssuedDate(checkValidIssuedDate(request.getIssuedDate()));
    updatingCandidateCertification.setExpiredDate(
        checkValidExpiredDate(request.getIssuedDate(), request.getExpiredDate()));
    return CandidateCertificationMapper.INSTANCE.toDto(
        candidateCertificationRepository.save(updatingCandidateCertification));
  }

  @Override
  public CandidateCertification convertRequestToEntity(CandidateCertificationRequest request) {
    return new CandidateCertification(
        null,
        candidateService.findById(request.getCandidateId()),
        certificationService.findById(request.getCertificationId()),
        request.getIssuedDate(),
        request.getExpiredDate());
  }

  @Override
  public Boolean isValidIssuedDate(LocalDate issuedDate) {
    return !LocalDate.now().isBefore(issuedDate);
  }

  @Override
  public Boolean isValidExpiredDate(LocalDate issuedDate, LocalDate expiredDate) {
    return !expiredDate.isBefore(issuedDate);
  }

  @Override
  public LocalDate checkValidIssuedDate(LocalDate issuedDate) {
    if (isValidIssuedDate(issuedDate)) {
      return issuedDate;
    } else {
      throw BusinessConstraintException.invalidIssuedDate();
    }
  }

  @Override
  public LocalDate checkValidExpiredDate(LocalDate issuedDate, LocalDate expiredDate) {
    if (isValidExpiredDate(issuedDate, expiredDate)) {
      return expiredDate;
    } else {
      throw BusinessConstraintException.invalidExpiredDate();
    }
  }
}
