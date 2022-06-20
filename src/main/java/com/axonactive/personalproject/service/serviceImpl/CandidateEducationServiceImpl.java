package com.axonactive.personalproject.service.serviceImpl;

import com.axonactive.personalproject.controller.request.CandidateEducationRequest;
import com.axonactive.personalproject.entity.CandidateEducation;
import com.axonactive.personalproject.exception.EntityNotFoundException;
import com.axonactive.personalproject.repository.CandidateEducationRepository;
import com.axonactive.personalproject.repository.CandidateRepository;
import com.axonactive.personalproject.repository.EducationRepository;
import com.axonactive.personalproject.service.CandidateEducationService;
import com.axonactive.personalproject.service.dto.CandidateEducationDto;
import com.axonactive.personalproject.service.mapper.CandidateEducationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CandidateEducationServiceImpl implements CandidateEducationService {
  @Autowired CandidateEducationRepository candidateEducationRepository;
  @Autowired CandidateRepository candidateRepository;
  @Autowired EducationRepository educationRepository;

  @Override
  public List<CandidateEducationDto> findAll() {
    return CandidateEducationMapper.INSTANCE.toDtos(candidateEducationRepository.findAll());
  }

  @Override
  public CandidateEducationDto findById(Integer id) throws EntityNotFoundException {
    return CandidateEducationMapper.INSTANCE.toDto(
        candidateEducationRepository
            .findById(id)
            .orElseThrow(EntityNotFoundException::candidateEducationNotFound));
  }

  @Override
  public List<CandidateEducationDto> findByCandidateId(Integer id) {
    return CandidateEducationMapper.INSTANCE.toDtos(
        candidateEducationRepository.findByCandidateId(id));
  }

  @Override
  public CandidateEducation add(CandidateEducationRequest request)
      throws EntityNotFoundException {
    return candidateEducationRepository.save(convertFromRequestToEntity(request));
  }

  @Override
  public CandidateEducationDto update(CandidateEducationRequest request, Integer id)
      throws EntityNotFoundException {
    CandidateEducation updatingCandidateEducation =
        candidateEducationRepository
            .findById(id)
            .orElseThrow(EntityNotFoundException::candidateEducationNotFound);
    updatingCandidateEducation.setCandidate(
        candidateRepository.findById(request.getCandidateId()).get());
    updatingCandidateEducation.setEducation(
        educationRepository.findById(request.getEducationId()).get());
    updatingCandidateEducation.setGraduationYear(request.getGraduationYear());

    return CandidateEducationMapper.INSTANCE.toDto(
        candidateEducationRepository.save(updatingCandidateEducation));
  }

  @Override
  public void deleteById(Integer id) throws EntityNotFoundException {
    CandidateEducationDto foundCandidateEducation = findById(id);
    candidateEducationRepository.deleteById(id);
  }

  @Override
  public CandidateEducation convertFromRequestToEntity(CandidateEducationRequest request)
      throws EntityNotFoundException {
    return new CandidateEducation(
        null,
        candidateRepository
            .findById(request.getCandidateId())
            .orElseThrow(EntityNotFoundException::candidateNotFound),
        educationRepository
            .findById(request.getEducationId())
            .orElseThrow(EntityNotFoundException::candidateEducationNotFound),
        request.getGraduationYear());
  }
}
