package com.axonactive.personalproject.service;

import com.axonactive.personalproject.controller.request.WorkingHistoryRecordRequest;
import com.axonactive.personalproject.entity.WorkingHistoryRecord;
import com.axonactive.personalproject.exception.ResourceNotFoundException;
import com.axonactive.personalproject.service.dto.WorkingHistoryRecordDto;

import java.time.LocalDate;
import java.util.List;

public interface WorkingHistoryRecordService {
    List<WorkingHistoryRecordDto> findAll();
    WorkingHistoryRecordDto findById(Integer id) throws ResourceNotFoundException;

    void deleteById(Integer id) throws ResourceNotFoundException;

    WorkingHistoryRecord add(WorkingHistoryRecordRequest request) throws ResourceNotFoundException;
    List<WorkingHistoryRecordDto> findByCandidateId(Integer id);
    WorkingHistoryRecordDto update (WorkingHistoryRecordRequest request,Integer id) throws ResourceNotFoundException;
    WorkingHistoryRecord convertFromRequestToEntity (WorkingHistoryRecordRequest request) throws ResourceNotFoundException;

    Boolean isValidJoinedDate (LocalDate joinDate);
    Boolean isValidResignedDate (LocalDate joinDate);


}
