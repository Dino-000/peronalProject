package com.axonactive.personalproject.controller;

import com.axonactive.personalproject.entity.WorkingHistoryRecord;

import java.util.List;
import java.util.Optional;

public interface WorkingHistoryRecordsService {
    List<WorkingHistoryRecord> findAll();
    Optional<WorkingHistoryRecord> findById(Integer id);

    void deleteById(Integer id);

    WorkingHistoryRecord saveWorkingHistoryRecord(WorkingHistoryRecord candidate);
}
