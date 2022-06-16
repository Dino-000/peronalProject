package com.axonactive.personalproject.service;

import com.axonactive.personalproject.entity.ApplicationForm;
import com.axonactive.personalproject.exception.ResourceNotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ApplicationFormService {
  List<ApplicationForm> findAll();

  ApplicationForm findById(Integer id) throws ResourceNotFoundException;

  void deleteById(Integer id);

  ApplicationForm saveApplicationForm(ApplicationForm applicationForm);

  List<ApplicationForm> findBySubmittedDateBetween(LocalDate fromDate, LocalDate untilDate);

  List<ApplicationForm> findFirstByCandidateIdOrderBySubmittedDateDesc(Integer Id);

  List<ApplicationForm> findByHiringRequestHiringManagerId(Integer Id);
}
