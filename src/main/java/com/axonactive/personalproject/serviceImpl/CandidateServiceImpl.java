package com.axonactive.personalproject.serviceImpl;

import com.axonactive.personalproject.repository.CandidateRepository;
import com.axonactive.personalproject.service.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {
@Autowired
    CandidateRepository candidateRepository;
}
