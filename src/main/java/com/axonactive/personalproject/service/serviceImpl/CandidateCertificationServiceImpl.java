package com.axonactive.personalproject.service.serviceImpl;

import com.axonactive.personalproject.controller.request.CandidateCertificationRequest;
import com.axonactive.personalproject.entity.CandidateCertification;
import com.axonactive.personalproject.exception.BusinessConstraintException;
import com.axonactive.personalproject.exception.EntityNotFoundException;
import com.axonactive.personalproject.repository.CandidateCertificationRepository;
import com.axonactive.personalproject.repository.CandidateRepository;
import com.axonactive.personalproject.repository.CertificationRepository;
import com.axonactive.personalproject.service.CandidateCertificationService;
import com.axonactive.personalproject.service.dto.CandidateCertificationDto;
import com.axonactive.personalproject.service.mapper.CandidateCertificationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CandidateCertificationServiceImpl implements CandidateCertificationService {
  @Autowired CandidateCertificationRepository candidateCertificationRepository;
  @Autowired CandidateRepository candidateRepository;
  @Autowired CertificationRepository certificationRepository;

  @Override
  public List<CandidateCertificationDto> findAll() {
    return CandidateCertificationMapper.INSTANCE.toDtos(candidateCertificationRepository.findAll());
  }

  @Override
  public CandidateCertificationDto findById(Integer id) throws EntityNotFoundException {
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
  public CandidateCertification add(CandidateCertificationRequest request)
      throws EntityNotFoundException {
    if (!isValidIssuedDate(request.getIssuedDate())) {
      log.info("Issued Date: " + request.getIssuedDate());
      throw BusinessConstraintException.invalidIssuedDate();
    }
    if (!isValidExpiredDate(request.getIssuedDate(), request.getExpiredDate())) {
      log.info("Issued Date: " + request.getIssuedDate());
      log.info("Expired Date: " + request.getExpiredDate());
      throw BusinessConstraintException.invalidExpiredDate();
    }
    return candidateCertificationRepository.save(convertRequestToEntity(request));
  }

  @Override
  public void deleteById(Integer id) throws EntityNotFoundException {
    CandidateCertificationDto deletingCandidateCertification = findById(id);
    candidateCertificationRepository.deleteById(id);
  }

  @Override
  public CandidateCertificationDto update(CandidateCertificationRequest request, Integer id)
      throws EntityNotFoundException {
    CandidateCertification updatingCandidateCertification =
        candidateCertificationRepository
            .findById(id)
            .orElseThrow(EntityNotFoundException::candidateCertificationNotFound);
    updatingCandidateCertification.setCandidate(
        candidateRepository
            .findById(request.getCandidateId())
            .orElseThrow(EntityNotFoundException::candidateNotFound));
    updatingCandidateCertification.setCertification(
        certificationRepository
            .findById(request.getCertificationId())
            .orElseThrow(EntityNotFoundException::certificationNotFound));

    if (!isValidIssuedDate(request.getIssuedDate())) {
      log.info("Issued Date: " + request.getIssuedDate());
      throw BusinessConstraintException.invalidIssuedDate();
    }
    updatingCandidateCertification.setIssuedDate(request.getIssuedDate());
    if (!isValidExpiredDate(request.getIssuedDate(), request.getExpiredDate())) {
      log.info("Issued Date: " + request.getIssuedDate());
      log.info("Expired Date: " + request.getExpiredDate());
      throw BusinessConstraintException.invalidExpiredDate();
    }
    updatingCandidateCertification.setExpiredDate(request.getExpiredDate());
    return CandidateCertificationMapper.INSTANCE.toDto(
        candidateCertificationRepository.save(updatingCandidateCertification));
  }

  @Override
  public CandidateCertification convertRequestToEntity(CandidateCertificationRequest request)
      throws EntityNotFoundException {
    return new CandidateCertification(
        null,
        candidateRepository
            .findById(request.getCandidateId())
            .orElseThrow(EntityNotFoundException::candidateNotFound),
        certificationRepository
            .findById(request.getCertificationId())
            .orElseThrow(EntityNotFoundException::certificationNotFound),
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
}
