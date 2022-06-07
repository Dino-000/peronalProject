package com.axonactive.personalproject.serviceImpl;

import com.axonactive.personalproject.repository.CertificationRepository;
import com.axonactive.personalproject.service.CertificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CertificationServiceImpl implements CertificationService {
@Autowired
    CertificationRepository certificationRepository;
}
