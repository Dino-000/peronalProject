package com.axonactive.personalproject.service.serviceImpl;

import com.axonactive.personalproject.controller.request.HiringRequestSkillSetRequest;
import com.axonactive.personalproject.entity.HiringRequestSkillSet;
import com.axonactive.personalproject.exception.ResourceNotFoundException;
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
  public HiringRequestSkillSetDto findById(Integer id) throws ResourceNotFoundException {
    return HiringRequestSkillSetMapper.INSTANCE.toDto(
        hiringRequestSkillSetRepository
            .findById(id)
            .orElseThrow(
                () ->
                    new ResourceNotFoundException(
                        "Can't not find HiringRequestSkillSet with that id.")));
  }

  @Override
  public void deleteById(Integer id) throws ResourceNotFoundException {
    findById(id);
    hiringRequestSkillSetRepository.deleteById(id);
  }

  @Override
  public HiringRequestSkillSet add(HiringRequestSkillSetRequest request)
      throws ResourceNotFoundException {
    {
      return hiringRequestSkillSetRepository.save(convertRequestToEntity(request));
    }
  }

  @Override
  public HiringRequestSkillSetDto update(HiringRequestSkillSetRequest request, Integer id)
      throws ResourceNotFoundException {
    HiringRequestSkillSet updatingHiringRequestSkillSet =
        hiringRequestSkillSetRepository
            .findById(id)
            .orElseThrow(
                () ->
                    new ResourceNotFoundException(
                        "Can't not find HiringRequestSkillSet with that id."));
    updatingHiringRequestSkillSet.setHiringRequest(
        hiringRequestRepository
            .findById(request.getHiringRequestId())
            .orElseThrow(
                () -> new ResourceNotFoundException("Can't not find HiringRequest with that id.")));
    updatingHiringRequestSkillSet.setSkillSet(
        skillSetRepository
            .findById(request.getSkillSetId())
            .orElseThrow(
                () -> new ResourceNotFoundException("Can't not find SkillSet with that id.")));
    return HiringRequestSkillSetMapper.INSTANCE.toDto(
        hiringRequestSkillSetRepository.save(updatingHiringRequestSkillSet));
  }

  @Override
  public HiringRequestSkillSet convertRequestToEntity(HiringRequestSkillSetRequest request)
      throws ResourceNotFoundException {
    return new HiringRequestSkillSet(
        null,
        hiringRequestRepository
            .findById(request.getHiringRequestId())
            .orElseThrow(
                () -> new ResourceNotFoundException("Cant not found Hiring Request with that id")),
        skillSetRepository
            .findById(request.getSkillSetId())
            .orElseThrow(
                () -> new ResourceNotFoundException("Cant not found SkillSet with that id")));
  }
}
