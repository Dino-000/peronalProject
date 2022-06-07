package com.axonactive.personalproject.serviceImpl;

import com.axonactive.personalproject.repository.WorkingHistoryRecordRepository;
import com.axonactive.personalproject.service.WorkingHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkingHistoryServiceImpl implements WorkingHistoryService {
    @Autowired
    WorkingHistoryRecordRepository workingHistoryRecordRepository;
}
