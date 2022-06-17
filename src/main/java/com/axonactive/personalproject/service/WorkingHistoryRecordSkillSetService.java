package com.axonactive.personalproject.service;

import com.axonactive.personalproject.controller.request.WorkingHistoryRecordSkillSetRequest;
import com.axonactive.personalproject.entity.WorkingHistoryRecordSkillSet;
import com.axonactive.personalproject.exception.ResourceNotFoundException;
import com.axonactive.personalproject.service.dto.WorkingHistoryRecordSkillSetDto;

import java.util.List;

public interface WorkingHistoryRecordSkillSetService {
    List<WorkingHistoryRecordSkillSetDto> findAll();
    WorkingHistoryRecordSkillSetDto findById(Integer id) throws ResourceNotFoundException;

    void deleteById(Integer id) throws ResourceNotFoundException;

    WorkingHistoryRecordSkillSet add (WorkingHistoryRecordSkillSetRequest request) throws ResourceNotFoundException;

    WorkingHistoryRecordSkillSetDto update(WorkingHistoryRecordSkillSetRequest request ,Integer id) throws ResourceNotFoundException;

    List<WorkingHistoryRecordSkillSetDto> findByWorkingHistoryRecordCandidateId(Integer id);

    WorkingHistoryRecordSkillSet convertRequestToEntity (WorkingHistoryRecordSkillSetRequest request) throws ResourceNotFoundException;
}
