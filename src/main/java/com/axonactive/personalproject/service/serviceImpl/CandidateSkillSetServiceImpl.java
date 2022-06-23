package com.axonactive.personalproject.service.serviceImpl;

import com.axonactive.personalproject.controller.request.CandidateSkillSetRequest;
import com.axonactive.personalproject.entity.CandidateSkillSet;
import com.axonactive.personalproject.exception.EntityNotFoundException;
import com.axonactive.personalproject.repository.CandidateSkillSetRepository;
import com.axonactive.personalproject.service.CandidateService;
import com.axonactive.personalproject.service.CandidateSkillSetService;
import com.axonactive.personalproject.service.SkillSetService;
import com.axonactive.personalproject.service.dto.CandidateSkillSetDto;
import com.axonactive.personalproject.service.mapper.CandidateSkillSetMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CandidateSkillSetServiceImpl implements CandidateSkillSetService {

  @Autowired CandidateSkillSetRepository candidateSkillSetRepository;
  @Autowired CandidateService candidateService;
  @Autowired SkillSetService skillSetService;

  @Override
  public List<CandidateSkillSetDto> findAll() {
    return CandidateSkillSetMapper.INSTANCE.toDtos(candidateSkillSetRepository.findAll());
  }

  @Override
  public CandidateSkillSetDto findById(Integer id) {
    return CandidateSkillSetMapper.INSTANCE.toDto(
        candidateSkillSetRepository
            .findById(id)
            .orElseThrow(EntityNotFoundException::candidateSkillSetNotFound));
  }

  @Override
  public List<CandidateSkillSetDto> findByCandidateId(Integer id) {
    return CandidateSkillSetMapper.INSTANCE.toDtos(
        candidateSkillSetRepository.findByCandidateId(id));
  }

  @Override
  public CandidateSkillSet add(CandidateSkillSetRequest request) {
    return candidateSkillSetRepository.save(convertRequestToEntity(request));
  }

  @Override
  public CandidateSkillSet convertRequestToEntity(CandidateSkillSetRequest request) {
    return new CandidateSkillSet(
        null,
        candidateService.findById(request.getCandidateId()),
        skillSetService.findById(request.getSkillSetId()));
  }

  @Override
  public CandidateSkillSetDto update(CandidateSkillSetRequest request, Integer id) {
    CandidateSkillSet updatingCandidateSkillSet =
        candidateSkillSetRepository
            .findById(id)
            .orElseThrow(EntityNotFoundException::candidateSkillSetNotFound);
    updatingCandidateSkillSet.setCandidate(candidateService.findById(request.getCandidateId()));
    updatingCandidateSkillSet.setSkillSet(skillSetService.findById(request.getSkillSetId()));
    return CandidateSkillSetMapper.INSTANCE.toDto(
        candidateSkillSetRepository.save(updatingCandidateSkillSet));
  }

  @Override
  public void deleteById(Integer id) {
    findById(id);
    candidateSkillSetRepository.deleteById(id);
  }
}
