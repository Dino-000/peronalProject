package com.axonactive.personalproject.service.serviceImpl;

import com.axonactive.personalproject.controller.request.WorkingHistoryRecordRequest;
import com.axonactive.personalproject.entity.WorkingHistoryRecord;
import com.axonactive.personalproject.exception.BusinessConstraintException;
import com.axonactive.personalproject.exception.ResourceNotFoundException;
import com.axonactive.personalproject.repository.CandidateRepository;
import com.axonactive.personalproject.repository.WorkingHistoryRecordRepository;
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
  @Autowired CandidateRepository candidateRepository;

  @Override
  public List<WorkingHistoryRecordDto> findAll() {
    return WorkingHistoryRecordMapper.INSTANCE.toDtos(workingHistoryRecordRepository.findAll());
  }

  @Override
  public WorkingHistoryRecordDto findById(Integer id) throws ResourceNotFoundException {
    return WorkingHistoryRecordMapper.INSTANCE.toDto(
        workingHistoryRecordRepository
            .findById(id)
            .orElseThrow(
                    ResourceNotFoundException::workingHistoryRecordNotFound));
  }

  @Override
  public void deleteById(Integer id) throws ResourceNotFoundException {
    findById(id);
    workingHistoryRecordRepository.deleteById(id);
  }

  @Override
  public WorkingHistoryRecord add(WorkingHistoryRecordRequest request)
      throws ResourceNotFoundException {

    return workingHistoryRecordRepository.save(convertFromRequestToEntity(request));
  }

  @Override
  public List<WorkingHistoryRecordDto> findByCandidateId(Integer id) {
    return WorkingHistoryRecordMapper.INSTANCE.toDtos(
        workingHistoryRecordRepository.findByCandidateId(id));
  }

  @Override
  public WorkingHistoryRecordDto update(WorkingHistoryRecordRequest request, Integer id)
      throws ResourceNotFoundException {
    if (!isValidJoinedDate(request.getJoinedDate())){
      log.info("Joined Date: "+request.getJoinedDate());
      throw BusinessConstraintException.invalidJoinedDate();
    }
    if (!isValidResignedDate(request.getResignationDate())){
      log.info("Joined Date: "+request.getResignationDate());
      throw BusinessConstraintException.invalidResignedDate();
    }
    WorkingHistoryRecord updatingWorkingHistoryRecord =
        workingHistoryRecordRepository
            .findById(id)
            .orElseThrow(
                    ResourceNotFoundException::workingHistoryRecordNotFound);
    updatingWorkingHistoryRecord.setCompanyName(request.getCompanyName());
    updatingWorkingHistoryRecord.setJoinedDate(request.getJoinedDate());
    updatingWorkingHistoryRecord.setResignationDate(request.getResignationDate());
    updatingWorkingHistoryRecord.setPosition(request.getPosition());
    updatingWorkingHistoryRecord.setProjectName(request.getProjectName());
    updatingWorkingHistoryRecord.setResponsibility(request.getResponsibility());
    updatingWorkingHistoryRecord.setClient(request.getClient());
    updatingWorkingHistoryRecord.setTeamSize(request.getTeamSize());
    updatingWorkingHistoryRecord.setReferencesPeoplePhone(request.getReferencesPeoplePhone());
    updatingWorkingHistoryRecord.setCandidate(
        candidateRepository
            .findById(request.getCandidateId())
            .orElseThrow(
                    ResourceNotFoundException::candidateNotFound));
    return WorkingHistoryRecordMapper.INSTANCE.toDto(updatingWorkingHistoryRecord);
  }

  @Override
  public WorkingHistoryRecord convertFromRequestToEntity(WorkingHistoryRecordRequest request)
      throws ResourceNotFoundException {
    if (!isValidJoinedDate(request.getJoinedDate())){
      log.info("Joined Date: "+request.getJoinedDate());
      throw BusinessConstraintException.invalidJoinedDate();
    }
    if (!isValidResignedDate(request.getResignationDate())){
      log.info("Joined Date: "+request.getResignationDate());
      throw BusinessConstraintException.invalidResignedDate();
    }

      return new WorkingHistoryRecord(
          null,
          request.getCompanyName(),
          request.getJoinedDate(),
          request.getResignationDate(),
          request.getPosition(),
          request.getProjectName(),
          request.getResponsibility(),
          request.getClient(),
          request.getTeamSize(),
          request.getJobType(),
          request.getReferencesPeoplePhone(),
          candidateRepository
              .findById(request.getCandidateId())
              .orElseThrow(ResourceNotFoundException::candidateNotFound));
  }

  @Override
  public Boolean isValidJoinedDate(LocalDate joinDate) {
    return null;
  }

  @Override
  public Boolean isValidResignedDate(LocalDate joinDate) {
    return null;
  }
}
