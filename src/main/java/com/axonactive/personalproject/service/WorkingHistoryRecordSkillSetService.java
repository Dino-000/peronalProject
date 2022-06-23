package com.axonactive.personalproject.service;

import com.axonactive.personalproject.controller.request.WorkingHistoryRecordSkillSetRequest;
import com.axonactive.personalproject.entity.WorkingHistoryRecordSkillSet;
import com.axonactive.personalproject.service.dto.WorkingHistoryRecordSkillSetDto;

import java.time.LocalDate;
import java.util.List;

public interface WorkingHistoryRecordSkillSetService {
  List<WorkingHistoryRecordSkillSetDto> findAll();

  WorkingHistoryRecordSkillSetDto findById(Integer id);

  void deleteById(Integer id);

  WorkingHistoryRecordSkillSet add(WorkingHistoryRecordSkillSetRequest request);

  WorkingHistoryRecordSkillSetDto update(WorkingHistoryRecordSkillSetRequest request, Integer id);

  List<WorkingHistoryRecordSkillSetDto> findByWorkingHistoryRecordCandidateId(Integer id);

  WorkingHistoryRecordSkillSet convertRequestToEntity(WorkingHistoryRecordSkillSetRequest request);

  boolean isValidJoinedDate(LocalDate date);

  boolean isResignedDate(LocalDate joinedDate, LocalDate resignDate);
}
