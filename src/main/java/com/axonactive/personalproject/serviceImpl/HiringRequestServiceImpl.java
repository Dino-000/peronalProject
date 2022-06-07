package com.axonactive.personalproject.serviceImpl;

import com.axonactive.personalproject.repository.HiringRequestRepository;
import com.axonactive.personalproject.service.HiringRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HiringRequestServiceImpl implements HiringRequestService {
@Autowired
    HiringRequestRepository hiringRequestRepository;
}
