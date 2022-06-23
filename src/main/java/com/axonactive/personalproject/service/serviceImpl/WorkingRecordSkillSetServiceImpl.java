package com.axonactive.personalproject.service.serviceImpl;

import com.axonactive.personalproject.controller.request.WorkingHistoryRecordSkillSetRequest;
import com.axonactive.personalproject.entity.WorkingHistoryRecordSkillSet;
import com.axonactive.personalproject.exception.EntityNotFoundException;
import com.axonactive.personalproject.repository.WorkingHistoryRecordSkillSetRepository;
import com.axonactive.personalproject.service.SkillSetService;
import com.axonactive.personalproject.service.WorkingHistoryRecordService;
import com.axonactive.personalproject.service.WorkingHistoryRecordSkillSetService;
import com.axonactive.personalproject.service.dto.WorkingHistoryRecordSkillSetDto;
import com.axonactive.personalproject.service.mapper.WorkingHistoryRecordSkillSetMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkingRecordSkillSetServiceImpl implements WorkingHistoryRecordSkillSetService {
  @Autowired WorkingHistoryRecordSkillSetRepository workingHistoryRecordSkillSetRepository;
  @Autowired WorkingHistoryRecordService workingHistoryRecordService;
  @Autowired SkillSetService skillSetService;

  @Override
  public List<WorkingHistoryRecordSkillSetDto> findAll() {
    return WorkingHistoryRecordSkillSetMapper.INSTANCE.toDtos(
        workingHistoryRecordSkillSetRepository.findAll());
  }

  @Override
  public WorkingHistoryRecordSkillSetDto findById(Integer id) {
    return WorkingHistoryRecordSkillSetMapper.INSTANCE.toDto(
        workingHistoryRecordSkillSetRepository
            .findById(id)
            .orElseThrow(EntityNotFoundException::workingHistoryRecordSkillSetNotFound));
  }

  @Override
  public void deleteById(Integer id) {
    findById(id);
    workingHistoryRecordSkillSetRepository.deleteById(id);
  }

  @Override
  public WorkingHistoryRecordSkillSet add(WorkingHistoryRecordSkillSetRequest request) {
    return workingHistoryRecordSkillSetRepository.save(convertRequestToEntity(request));
  }

  @Override
  public WorkingHistoryRecordSkillSetDto update(
      WorkingHistoryRecordSkillSetRequest request, Integer id) {
    WorkingHistoryRecordSkillSet updatingWorkingHistoryRecordSkillSet =
        workingHistoryRecordSkillSetRepository
            .findById(id)
            .orElseThrow(EntityNotFoundException::workingHistoryRecordSkillSetNotFound);
    updatingWorkingHistoryRecordSkillSet.setSkillSet(
        skillSetService.findById(request.getSkillSetId()));
    updatingWorkingHistoryRecordSkillSet.setWorkingHistoryRecord(
        workingHistoryRecordService.checkWorkingHistoryRecordId(
            request.getWorkingHistoryRecordId()));
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
      WorkingHistoryRecordSkillSetRequest request) {
    return new WorkingHistoryRecordSkillSet(
        null,
        workingHistoryRecordService.checkWorkingHistoryRecordId(
            request.getWorkingHistoryRecordId()),
        skillSetService.findById(request.getSkillSetId()));
  }

  @Override
  public boolean isValidJoinedDate(LocalDate date) {
    return !LocalDate.now().isBefore(date);
  }

  @Override
  public boolean isResignedDate(LocalDate joinedDate, LocalDate resignDate) {
    return !resignDate.isBefore(joinedDate);
  }
}
