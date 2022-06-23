package com.axonactive.personalproject.service.serviceImpl;

import com.axonactive.personalproject.controller.request.WorkingHistoryRecordRequest;
import com.axonactive.personalproject.entity.WorkingHistoryRecord;
import com.axonactive.personalproject.exception.BusinessConstraintException;
import com.axonactive.personalproject.exception.EntityNotFoundException;
import com.axonactive.personalproject.repository.WorkingHistoryRecordRepository;
import com.axonactive.personalproject.service.CandidateService;
import com.axonactive.personalproject.service.WorkingHistoryRecordService;
import com.axonactive.personalproject.service.dto.WorkingHistoryRecordDto;
import com.axonactive.personalproject.service.mapper.WorkingHistoryRecordMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WorkingHistoryRecordServiceImpl implements WorkingHistoryRecordService {
  @Autowired WorkingHistoryRecordRepository workingHistoryRecordRepository;
  @Autowired CandidateService candidateService;

  @Override
  public List<WorkingHistoryRecordDto> findAll() {
    return WorkingHistoryRecordMapper.INSTANCE.toDtos(workingHistoryRecordRepository.findAll());
  }

  @Override
  public WorkingHistoryRecordDto findById(Integer id) {
    return WorkingHistoryRecordMapper.INSTANCE.toDto(
        workingHistoryRecordRepository
            .findById(id)
            .orElseThrow(EntityNotFoundException::workingHistoryRecordNotFound));
  }

  @Override
  public void deleteById(Integer id) {
    findById(id);
    workingHistoryRecordRepository.deleteById(id);
  }

  @Override
  public WorkingHistoryRecord add(WorkingHistoryRecordRequest request) {

    return workingHistoryRecordRepository.save(convertFromRequestToEntity(request));
  }

  @Override
  public List<WorkingHistoryRecordDto> findByCandidateId(Integer id) {
    return WorkingHistoryRecordMapper.INSTANCE.toDtos(
        workingHistoryRecordRepository.findByCandidateId(id));
  }

  @Override
  public WorkingHistoryRecordDto update(WorkingHistoryRecordRequest request, Integer id) {
    if (!isValidJoinedDate(request.getJoinedDate())) {
      log.info("Joined Date: " + request.getJoinedDate());
      throw BusinessConstraintException.invalidJoinedDate();
    }
    if (!isValidResignedDate(request.getJoinedDate(), request.getResignationDate())) {
      log.info("Joined Date: " + request.getJoinedDate());
      log.info("Resigned Date: " + request.getResignationDate());
      throw BusinessConstraintException.invalidResignedDate();
    }
    WorkingHistoryRecord updatingWorkingHistoryRecord =
        workingHistoryRecordRepository
            .findById(id)
            .orElseThrow(EntityNotFoundException::workingHistoryRecordNotFound);
    updatingWorkingHistoryRecord.setCompanyName(request.getCompanyName());
    updatingWorkingHistoryRecord.setJoinedDate(checkValidJoinedDate(request.getJoinedDate()));
    updatingWorkingHistoryRecord.setResignationDate(
        checkValidResignedDate(request.getJoinedDate(), request.getResignationDate()));
    updatingWorkingHistoryRecord.setPosition(request.getPosition());
    updatingWorkingHistoryRecord.setProjectName(request.getProjectName());
    updatingWorkingHistoryRecord.setResponsibility(request.getResponsibility());
    updatingWorkingHistoryRecord.setClient(request.getClient());
    updatingWorkingHistoryRecord.setTeamSize(request.getTeamSize());
    updatingWorkingHistoryRecord.setReferencesPeoplePhone(request.getReferencesPeoplePhone());
    updatingWorkingHistoryRecord.setCandidate(candidateService.findById(request.getCandidateId()));
    return WorkingHistoryRecordMapper.INSTANCE.toDto(updatingWorkingHistoryRecord);
  }

  @Override
  public WorkingHistoryRecord convertFromRequestToEntity(WorkingHistoryRecordRequest request) {

    return new WorkingHistoryRecord(
        null,
        request.getCompanyName(),
        checkValidJoinedDate(request.getJoinedDate()),
        checkValidResignedDate(request.getJoinedDate(), request.getResignationDate()),
        request.getPosition(),
        request.getProjectName(),
        request.getResponsibility(),
        request.getClient(),
        request.getTeamSize(),
        request.getJobType(),
        request.getReferencesPeoplePhone(),
        candidateService.findById(request.getCandidateId()));
  }

  @Override
  public Boolean isValidJoinedDate(LocalDate joinDate) {
    return !LocalDate.now().isBefore(joinDate);
  }

  @Override
  public Boolean isValidResignedDate(LocalDate joinDate, LocalDate resignedDate) {
    return !resignedDate.isBefore(joinDate);
  }

  @Override
  public WorkingHistoryRecord checkWorkingHistoryRecordId(Integer id) {
    return workingHistoryRecordRepository
        .findById(id)
        .orElseThrow(EntityNotFoundException::workingHistoryRecordNotFound);
  }

  public LocalDate checkValidJoinedDate(LocalDate joinDate) {
    if (isValidJoinedDate(joinDate)) {
      return joinDate;
    } else {
      throw BusinessConstraintException.invalidJoinedDate();
    }
  }

  public LocalDate checkValidResignedDate(LocalDate joinDate, LocalDate resignDate) {
    if (isValidResignedDate(joinDate, resignDate)) {
      return resignDate;
    } else {
      throw BusinessConstraintException.invalidResignedDate();
    }
  }
}
