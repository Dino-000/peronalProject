package com.axonactive.personalproject.service;

import com.axonactive.personalproject.controller.request.WorkingHistoryRecordSkillSetRequest;
import com.axonactive.personalproject.entity.WorkingHistoryRecordSkillSet;
import com.axonactive.personalproject.exception.EntityNotFoundException;
import com.axonactive.personalproject.service.dto.WorkingHistoryRecordSkillSetDto;

import java.time.LocalDate;
import java.util.List;

public interface WorkingHistoryRecordSkillSetService {
  List<WorkingHistoryRecordSkillSetDto> findAll();

  WorkingHistoryRecordSkillSetDto findById(Integer id) throws EntityNotFoundException;

  void deleteById(Integer id) throws EntityNotFoundException;

  WorkingHistoryRecordSkillSet add(WorkingHistoryRecordSkillSetRequest request)
      throws EntityNotFoundException;

  WorkingHistoryRecordSkillSetDto update(WorkingHistoryRecordSkillSetRequest request, Integer id)
      throws EntityNotFoundException;

  List<WorkingHistoryRecordSkillSetDto> findByWorkingHistoryRecordCandidateId(Integer id);

  WorkingHistoryRecordSkillSet convertRequestToEntity(WorkingHistoryRecordSkillSetRequest request)
      throws EntityNotFoundException;

  boolean isValidJoinedDate(LocalDate date);

  boolean isResignedDate(LocalDate joinedDate, LocalDate resignDate);
}
