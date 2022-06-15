package com.axonactive.personalproject.service;

import com.axonactive.personalproject.entity.Education;

import java.util.List;
import java.util.Optional;

public interface EducationService {
    List<Education> findAll();
    Optional<Education> findById(Integer id);

    void deleteById(Integer id);

    Education saveEducation(Education education);

    List<Education> findByCandidateId(Integer id);

}
