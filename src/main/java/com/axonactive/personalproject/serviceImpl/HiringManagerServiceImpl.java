package com.axonactive.personalproject.serviceImpl;

import com.axonactive.personalproject.repository.HiringManagerRepository;
import com.axonactive.personalproject.service.HiringManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HiringManagerServiceImpl implements HiringManagerService {
@Autowired
    HiringManagerRepository hiringManagerRepository;
}
