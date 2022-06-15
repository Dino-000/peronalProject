package com.axonactive.personalproject.service;

import com.axonactive.personalproject.entity.Candidate;
import com.axonactive.personalproject.entity.WorkingHistoryRecord;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface WorkingHistoryRecordService {
    List<WorkingHistoryRecord> findAll();
    Optional<WorkingHistoryRecord> findById(Integer id);

    void deleteById(Integer id);

    WorkingHistoryRecord saveWorkingHistoryRecord(WorkingHistoryRecord workingHistoryRecord);
    List<WorkingHistoryRecord> findByCandidateId(Integer id);

}
