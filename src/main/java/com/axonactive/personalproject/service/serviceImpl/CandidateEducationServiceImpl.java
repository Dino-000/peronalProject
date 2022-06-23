package com.axonactive.personalproject.service.serviceImpl;

import com.axonactive.personalproject.controller.request.CandidateEducationRequest;
import com.axonactive.personalproject.entity.CandidateEducation;
import com.axonactive.personalproject.exception.EntityNotFoundException;
import com.axonactive.personalproject.repository.CandidateEducationRepository;
import com.axonactive.personalproject.service.CandidateEducationService;
import com.axonactive.personalproject.service.CandidateService;
import com.axonactive.personalproject.service.EducationService;
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
  @Autowired CandidateService candidateService;
  @Autowired EducationService educationService;

  @Override
  public List<CandidateEducationDto> findAll() {
    return CandidateEducationMapper.INSTANCE.toDtos(candidateEducationRepository.findAll());
  }

  @Override
  public CandidateEducationDto findById(Integer id) {
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
  public CandidateEducation add(CandidateEducationRequest request) {
    return candidateEducationRepository.save(convertFromRequestToEntity(request));
  }

  @Override
  public CandidateEducationDto update(CandidateEducationRequest request, Integer id) {
    CandidateEducation updatingCandidateEducation =
        candidateEducationRepository
            .findById(id)
            .orElseThrow(EntityNotFoundException::candidateEducationNotFound);
    updatingCandidateEducation.setCandidate(candidateService.findById(request.getCandidateId()));
    updatingCandidateEducation.setEducation(educationService.findById(request.getEducationId()));
    updatingCandidateEducation.setGraduationYear(request.getGraduationYear());

    return CandidateEducationMapper.INSTANCE.toDto(
        candidateEducationRepository.save(updatingCandidateEducation));
  }

  @Override
  public void deleteById(Integer id) {
    findById(id);
    candidateEducationRepository.deleteById(id);
  }

  @Override
  public CandidateEducation convertFromRequestToEntity(CandidateEducationRequest request) {
    return new CandidateEducation(
        null,
        candidateService.findById(request.getCandidateId()),
        educationService.findById(request.getEducationId()),
        request.getGraduationYear());
  }
}
