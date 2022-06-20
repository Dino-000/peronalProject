package com.axonactive.personalproject.service;

import com.axonactive.personalproject.controller.request.ApplicationFormRequest;
import com.axonactive.personalproject.entity.ApplicationForm;
import com.axonactive.personalproject.exception.ResourceNotFoundException;
import com.axonactive.personalproject.service.dto.ApplicationFormDto;

import java.time.LocalDate;
import java.util.List;

public interface ApplicationFormService {
  List<ApplicationFormDto> findAll();

  ApplicationFormDto findById(Integer id) throws ResourceNotFoundException;

  void deleteById(Integer id) throws ResourceNotFoundException;

  ApplicationForm add(ApplicationFormRequest request) throws ResourceNotFoundException;

  ApplicationForm convertRequestToEntity(ApplicationFormRequest request) throws ResourceNotFoundException;

  ApplicationFormDto save(ApplicationFormRequest request) throws ResourceNotFoundException;

  List<ApplicationFormDto> findBySubmittedDateBetween(String fromDate, String toDate);

//  List<ApplicationFormDto> findFirstByCandidateIdOrderBySubmittedDateDesc(Integer Id);

  List<ApplicationFormDto> findByHiringRequestHiringManagerId(Integer Id);

  ApplicationFormDto update(Integer id,ApplicationFormRequest updateForm) throws ResourceNotFoundException;

  Double getSalary (Integer id) throws ResourceNotFoundException;

  Boolean isValidHrOfficer (ApplicationForm applicationForm);

  Boolean isValidSubmittedDate(LocalDate date);
}
