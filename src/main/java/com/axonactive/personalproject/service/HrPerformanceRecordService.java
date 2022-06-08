package com.axonactive.personalproject.service;

import com.axonactive.personalproject.entity.HrPerformanceRecord;

import java.util.List;
import java.util.Optional;

public interface HrPerformanceRecordService {
    List<HrPerformanceRecord> findAll();
    Optional<HrPerformanceRecord> findById(Integer id);

    void deleteById(Integer id);

    HrPerformanceRecord saveHrPerformanceRecord(HrPerformanceRecord hrPerformance);
}
