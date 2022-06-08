package com.axonactive.personalproject.service.serviceImpl;

import com.axonactive.personalproject.entity.HrPerformanceRecord;
import com.axonactive.personalproject.repository.HrPerformanceRecordRepository;
import com.axonactive.personalproject.service.HrPerformanceRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HrPerformanceRecordServiceImpl implements HrPerformanceRecordService {
@Autowired
    HrPerformanceRecordRepository hrPerformanceRecordRepository;

    @Override
    public List<HrPerformanceRecord> findAll() {
        return hrPerformanceRecordRepository.findAll();
    }

    @Override
    public Optional<HrPerformanceRecord> findById(Integer id) {
        return hrPerformanceRecordRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        hrPerformanceRecordRepository.deleteById(id);
    }

    @Override
    public HrPerformanceRecord saveHrPerformanceRecord(HrPerformanceRecord hrPerformance) {
        return hrPerformanceRecordRepository.save(hrPerformance);
    }
}
