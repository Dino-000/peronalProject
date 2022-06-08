package com.axonactive.personalproject.service.serviceImpl;

import com.axonactive.personalproject.entity.HrPerformanceRecords;
import com.axonactive.personalproject.repository.HrPerformanceRecordsRepository;
import com.axonactive.personalproject.service.HrPerformanceRecordsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HrPerformanceRecordsServiceImpl implements HrPerformanceRecordsService {
@Autowired
    HrPerformanceRecordsRepository hrPerformanceRecordsRepository;

    @Override
    public List<HrPerformanceRecords> findAll() {
        return hrPerformanceRecordsRepository.findAll();
    }

    @Override
    public Optional<HrPerformanceRecords> findById(Integer id) {
        return hrPerformanceRecordsRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        hrPerformanceRecordsRepository.deleteById(id);
    }

    @Override
    public HrPerformanceRecords saveHrPerformanceRecords(HrPerformanceRecords hrPerformance) {
        return hrPerformanceRecordsRepository.save(hrPerformance);
    }
}
