package com.axonactive.personalproject.service;

import com.axonactive.personalproject.controller.request.WorkingHistoryRecordRequest;
import com.axonactive.personalproject.entity.WorkingHistoryRecord;
import com.axonactive.personalproject.exception.EntityNotFoundException;
import com.axonactive.personalproject.service.dto.WorkingHistoryRecordDto;

import java.time.LocalDate;
import java.util.List;

public interface WorkingHistoryRecordService {
  List<WorkingHistoryRecordDto> findAll();

  WorkingHistoryRecordDto findById(Integer id) throws EntityNotFoundException;

  void deleteById(Integer id) throws EntityNotFoundException;

  WorkingHistoryRecord add(WorkingHistoryRecordRequest request) throws EntityNotFoundException;

  List<WorkingHistoryRecordDto> findByCandidateId(Integer id);

  WorkingHistoryRecordDto update(WorkingHistoryRecordRequest request, Integer id)
      throws EntityNotFoundException;

  WorkingHistoryRecord convertFromRequestToEntity(WorkingHistoryRecordRequest request)
      throws EntityNotFoundException;

  Boolean isValidJoinedDate(LocalDate joinDate);

  Boolean isValidResignedDate(LocalDate joinDate, LocalDate resignedDate);
}
