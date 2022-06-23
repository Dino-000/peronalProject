package com.axonactive.personalproject.service;

import com.axonactive.personalproject.entity.Education;

import java.util.List;

public interface EducationService {
  List<Education> findAll();

  Education findById(Integer id);

  void deleteById(Integer id);

  Education add(Education inputData);

  List<Education> findByCandidateId(Integer id);

  Education update(Education inputData, Integer id);
}
