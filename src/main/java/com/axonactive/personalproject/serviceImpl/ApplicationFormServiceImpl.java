package com.axonactive.personalproject.serviceImpl;

import com.axonactive.personalproject.repository.ApplicationFormRepository;
import com.axonactive.personalproject.service.ApplicationFormService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationFormServiceImpl implements ApplicationFormService {
@Autowired
    ApplicationFormRepository applicationFormRepository;
}