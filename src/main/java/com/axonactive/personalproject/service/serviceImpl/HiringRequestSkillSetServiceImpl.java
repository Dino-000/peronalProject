package com.axonactive.personalproject.service.serviceImpl;

import com.axonactive.personalproject.controller.request.HiringRequestSkillSetRequest;
import com.axonactive.personalproject.entity.HiringRequestSkillSet;
import com.axonactive.personalproject.exception.EntityNotFoundException;
import com.axonactive.personalproject.repository.HiringRequestRepository;
import com.axonactive.personalproject.repository.HiringRequestSkillSetRepository;
import com.axonactive.personalproject.repository.SkillSetRepository;
import com.axonactive.personalproject.service.HiringRequestSkillSetService;
import com.axonactive.personalproject.service.dto.HiringRequestSkillSetDto;
import com.axonactive.personalproject.service.mapper.HiringRequestSkillSetMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HiringRequestSkillSetServiceImpl implements HiringRequestSkillSetService {
  @Autowired HiringRequestSkillSetRepository hiringRequestSkillSetRepository;
  @Autowired HiringRequestRepository hiringRequestRepository;
  @Autowired SkillSetRepository skillSetRepository;

  @Override
  public List<HiringRequestSkillSetDto> findAll() {
    return HiringRequestSkillSetMapper.INSTANCE.toDtos(hiringRequestSkillSetRepository.findAll());
  }

  @Override
  public HiringRequestSkillSetDto findById(Integer id) {
    return HiringRequestSkillSetMapper.INSTANCE.toDto(
        hiringRequestSkillSetRepository
            .findById(id)
            .orElseThrow(EntityNotFoundException::hiringRequestSkillSetNotFound));
  }

  @Override
  public void deleteById(Integer id) {
    findById(id);
    hiringRequestSkillSetRepository.deleteById(id);
  }

  @Override
  public HiringRequestSkillSet add(HiringRequestSkillSetRequest request) {
    {
      return hiringRequestSkillSetRepository.save(convertRequestToEntity(request));
    }
  }

  @Override
  public HiringRequestSkillSetDto update(HiringRequestSkillSetRequest request, Integer id) {
    HiringRequestSkillSet updatingHiringRequestSkillSet =
        hiringRequestSkillSetRepository
            .findById(id)
            .orElseThrow(EntityNotFoundException::hiringRequestSkillSetNotFound);
    updatingHiringRequestSkillSet.setHiringRequest(
        hiringRequestRepository
            .findById(request.getHiringRequestId())
            .orElseThrow(EntityNotFoundException::hiringRequestNotFound));
    updatingHiringRequestSkillSet.setSkillSet(
        skillSetRepository
            .findById(request.getSkillSetId())
            .orElseThrow(EntityNotFoundException::skillSetNotFound));
    return HiringRequestSkillSetMapper.INSTANCE.toDto(
        hiringRequestSkillSetRepository.save(updatingHiringRequestSkillSet));
  }

  @Override
  public HiringRequestSkillSet convertRequestToEntity(HiringRequestSkillSetRequest request) {
    return new HiringRequestSkillSet(
        null,
        hiringRequestRepository
            .findById(request.getHiringRequestId())
            .orElseThrow(EntityNotFoundException::hiringRequestNotFound),
        skillSetRepository
            .findById(request.getSkillSetId())
            .orElseThrow(EntityNotFoundException::hiringRequestNotFound));
  }
}
