package com.axonactive.personalproject.service;

import com.axonactive.personalproject.entity.Education;
import com.axonactive.personalproject.exception.EntityNotFoundException;

import java.util.List;

public interface EducationService {
  List<Education> findAll();

  Education findById(Integer id) throws EntityNotFoundException;

  void deleteById(Integer id) throws EntityNotFoundException;

  Education saveEducation(Education inputData);

  List<Education> findByCandidateId(Integer id);

  Education update(Education inputData, Integer id) throws EntityNotFoundException;
}
