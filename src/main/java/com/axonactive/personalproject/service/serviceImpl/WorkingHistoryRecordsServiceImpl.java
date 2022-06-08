package com.axonactive.personalproject.service.serviceImpl;

import com.axonactive.personalproject.entity.WorkingHistoryRecord;
import com.axonactive.personalproject.repository.WorkingHistoryRecordRepository;
import com.axonactive.personalproject.service.WorkingHistoryRecordsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkingHistoryRecordsServiceImpl implements WorkingHistoryRecordsService {
  @Autowired WorkingHistoryRecordRepository workingHistoryRecordRepository;

  @Override
  public List<WorkingHistoryRecord> findAll() {
    return workingHistoryRecordRepository.findAll();
  }

  @Override
  public Optional<WorkingHistoryRecord> findById(Integer id) {
    return workingHistoryRecordRepository.findById(id);
  }

  @Override
  public void deleteById(Integer id) {
    workingHistoryRecordRepository.deleteById(id);
  }

  @Override
  public WorkingHistoryRecord saveWorkingHistoryRecord(WorkingHistoryRecord candidate) {
    return workingHistoryRecordRepository.save(candidate);
  }
}
