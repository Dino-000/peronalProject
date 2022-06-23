package com.axonactive.personalproject.service;

import com.axonactive.personalproject.controller.request.WorkingHistoryRecordRequest;
import com.axonactive.personalproject.entity.WorkingHistoryRecord;
import com.axonactive.personalproject.service.dto.WorkingHistoryRecordDto;

import java.time.LocalDate;
import java.util.List;

public interface WorkingHistoryRecordService {
  List<WorkingHistoryRecordDto> findAll();

  WorkingHistoryRecordDto findById(Integer id);

  void deleteById(Integer id);

  WorkingHistoryRecord add(WorkingHistoryRecordRequest request);

  List<WorkingHistoryRecordDto> findByCandidateId(Integer id);

  WorkingHistoryRecordDto update(WorkingHistoryRecordRequest request, Integer id);

  WorkingHistoryRecord convertFromRequestToEntity(WorkingHistoryRecordRequest request);

  Boolean isValidJoinedDate(LocalDate joinDate);

  Boolean isValidResignedDate(LocalDate joinDate, LocalDate resignedDate);

  WorkingHistoryRecord checkWorkingHistoryRecordId(Integer id);
}
