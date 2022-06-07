package com.axonactive.personalproject.service;

import com.axonactive.personalproject.entity.HrPerformanceRecords;

import java.util.List;
import java.util.Optional;

public interface HrPerformanceRecordsService {
    List<HrPerformanceRecords> findAll();
    Optional<HrPerformanceRecords> findById(Integer id);

    void deleteById(Integer id);

    HrPerformanceRecords saveHrPerformanceRecords(HrPerformanceRecords hrPerformance);
}
