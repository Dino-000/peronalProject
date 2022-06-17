package com.axonactive.personalproject.service.serviceImpl;

import com.axonactive.personalproject.controller.request.WorkingHistoryRecordSkillSetRequest;
import com.axonactive.personalproject.entity.WorkingHistoryRecordSkillSet;
import com.axonactive.personalproject.exception.ResourceNotFoundException;
import com.axonactive.personalproject.repository.SkillSetRepository;
import com.axonactive.personalproject.repository.WorkingHistoryRecordRepository;
import com.axonactive.personalproject.repository.WorkingHistoryRecordSkillSetRepository;
import com.axonactive.personalproject.service.WorkingHistoryRecordSkillSetService;
import com.axonactive.personalproject.service.dto.WorkingHistoryRecordSkillSetDto;
import com.axonactive.personalproject.service.mapper.WorkingHistoryRecordSkillSetMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkingRecordSkillSetServiceImpl implements WorkingHistoryRecordSkillSetService {
  @Autowired WorkingHistoryRecordSkillSetRepository workingHistoryRecordSkillSetRepository;
  @Autowired WorkingHistoryRecordRepository workingHistoryRecordRepository;
  @Autowired SkillSetRepository skillSetRepository;

  @Override
  public List<WorkingHistoryRecordSkillSetDto> findAll() {
    return WorkingHistoryRecordSkillSetMapper.INSTANCE.toDtos(
        workingHistoryRecordSkillSetRepository.findAll());
  }

  @Override
  public WorkingHistoryRecordSkillSetDto findById(Integer id) throws ResourceNotFoundException {
    return WorkingHistoryRecordSkillSetMapper.INSTANCE.toDto(
        workingHistoryRecordSkillSetRepository
            .findById(id)
            .orElseThrow(
                () ->
                    new ResourceNotFoundException(
                        "Can't not find Working History Record SkillSet with that id.")));
  }

  @Override
  public void deleteById(Integer id) throws ResourceNotFoundException {
    findById(id);
    workingHistoryRecordSkillSetRepository.deleteById(id);
  }

  @Override
  public WorkingHistoryRecordSkillSet add(WorkingHistoryRecordSkillSetRequest request)
      throws ResourceNotFoundException {
    return workingHistoryRecordSkillSetRepository.save(convertRequestToEntity(request));
  }

  @Override
  public WorkingHistoryRecordSkillSetDto update(
      WorkingHistoryRecordSkillSetRequest request, Integer id) throws ResourceNotFoundException {
    WorkingHistoryRecordSkillSet updatingWorkingHistoryRecordSkillSet =
        workingHistoryRecordSkillSetRepository
            .findById(id)
            .orElseThrow(
                () ->
                    new ResourceNotFoundException(
                        "Can't not find Working History Record SkillSet with that id."));
    updatingWorkingHistoryRecordSkillSet.setSkillSet(
        skillSetRepository
            .findById(request.getSkillSetId())
            .orElseThrow(
                () -> new ResourceNotFoundException("Can't not find SkillSet with that id.")));
    updatingWorkingHistoryRecordSkillSet.setWorkingHistoryRecord(
        workingHistoryRecordRepository
            .findById(request.getWorkingHistoryRecordId())
            .orElseThrow(
                () ->
                    new ResourceNotFoundException(
                        "Can't not find Working History Record with that id.")));
    return WorkingHistoryRecordSkillSetMapper.INSTANCE.toDto(
        workingHistoryRecordSkillSetRepository.save(updatingWorkingHistoryRecordSkillSet));
  }

  @Override
  public List<WorkingHistoryRecordSkillSetDto> findByWorkingHistoryRecordCandidateId(Integer id) {
    return WorkingHistoryRecordSkillSetMapper.INSTANCE.toDtos(
        workingHistoryRecordSkillSetRepository.findByWorkingHistoryRecordCandidateId(id));
  }

  @Override
  public WorkingHistoryRecordSkillSet convertRequestToEntity(
      WorkingHistoryRecordSkillSetRequest request) throws ResourceNotFoundException {
    return new WorkingHistoryRecordSkillSet(
        null,
        workingHistoryRecordRepository
            .findById(request.getWorkingHistoryRecordId())
            .orElseThrow(
                () ->
                    new ResourceNotFoundException(
                        "Can't not find Working History Record with that id.")),
        skillSetRepository
            .findById(request.getSkillSetId())
            .orElseThrow(
                () -> new ResourceNotFoundException("Can't not find SkillSet with that id.")));
  }
}
