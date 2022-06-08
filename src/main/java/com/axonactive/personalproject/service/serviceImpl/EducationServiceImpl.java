package com.axonactive.personalproject.service.serviceImpl;

import com.axonactive.personalproject.entity.Education;
import com.axonactive.personalproject.repository.EducationRepository;
import com.axonactive.personalproject.service.EducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EducationServiceImpl implements EducationService {
@Autowired
    EducationRepository educationRepository;

    @Override
    public List<Education> findAll() {
        return educationRepository.findAll();
    }

    @Override
    public Optional<Education> findById(Integer id) {
        return educationRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        educationRepository.deleteById(id);
    }

    @Override
    public Education saveEducation(Education education) {
        return educationRepository.save(education);
    }
}
