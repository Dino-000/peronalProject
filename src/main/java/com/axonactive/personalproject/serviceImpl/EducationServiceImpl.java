package com.axonactive.personalproject.serviceImpl;

import com.axonactive.personalproject.repository.EducationRepository;
import com.axonactive.personalproject.service.EducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EducationServiceImpl implements EducationService {
@Autowired
    EducationRepository educationRepository;
}
