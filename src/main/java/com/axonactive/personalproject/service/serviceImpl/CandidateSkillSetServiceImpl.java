package com.axonactive.personalproject.service.serviceImpl;

import com.axonactive.personalproject.controller.request.CandidateSkillSetRequest;
import com.axonactive.personalproject.entity.CandidateSkillSet;
import com.axonactive.personalproject.exception.EntityNotFoundException;
import com.axonactive.personalproject.repository.CandidateRepository;
import com.axonactive.personalproject.repository.CandidateSkillSetRepository;
import com.axonactive.personalproject.repository.SkillSetRepository;
import com.axonactive.personalproject.service.CandidateSkillSetService;
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
  @Autowired CandidateRepository candidateRepository;
  @Autowired SkillSetRepository skillSetRepository;

  @Override
  public List<CandidateSkillSetDto> findAll() {
    return CandidateSkillSetMapper.INSTANCE.toDtos(candidateSkillSetRepository.findAll());
  }

  @Override
  public CandidateSkillSetDto findById(Integer id) throws EntityNotFoundException {
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
  public CandidateSkillSet add(CandidateSkillSetRequest request) throws EntityNotFoundException {
    return candidateSkillSetRepository.save(convertRequestToEntity(request));
  }

  @Override
  public CandidateSkillSet convertRequestToEntity(CandidateSkillSetRequest request)
      throws EntityNotFoundException {
    return new CandidateSkillSet(
        null,
        candidateRepository
            .findById(request.getCandidateId())
            .orElseThrow(EntityNotFoundException::candidateNotFound),
        skillSetRepository
            .findById(request.getSkillSetId())
            .orElseThrow(EntityNotFoundException::skillSetNotFound));
  }

  @Override
  public CandidateSkillSetDto update(CandidateSkillSetRequest request, Integer id)
      throws EntityNotFoundException {
    CandidateSkillSet updatingCandidateSkillSet =
        candidateSkillSetRepository
            .findById(id)
            .orElseThrow(EntityNotFoundException::candidateSkillSetNotFound);
    updatingCandidateSkillSet.setCandidate(
        candidateRepository
            .findById(request.getCandidateId())
            .orElseThrow(EntityNotFoundException::candidateNotFound));
    updatingCandidateSkillSet.setSkillSet(
        skillSetRepository
            .findById(request.getSkillSetId())
            .orElseThrow(EntityNotFoundException::skillSetNotFound));
    return CandidateSkillSetMapper.INSTANCE.toDto(
        candidateSkillSetRepository.save(updatingCandidateSkillSet));
  }

  @Override
  public void deleteById(Integer id) throws EntityNotFoundException {
    CandidateSkillSet deletingCandidateSkillSet =
        candidateSkillSetRepository
            .findById(id)
            .orElseThrow(EntityNotFoundException::applicationFormNotFound);

    candidateSkillSetRepository.deleteById(id);
  }
}
