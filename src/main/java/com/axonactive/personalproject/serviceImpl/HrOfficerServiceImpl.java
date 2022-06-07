package com.axonactive.personalproject.serviceImpl;

import com.axonactive.personalproject.repository.HrOfficerRepository;
import com.axonactive.personalproject.service.HrOfficerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HrOfficerServiceImpl implements HrOfficerService {
@Autowired
    HrOfficerRepository hrOfficerRepository;
}
