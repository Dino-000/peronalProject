package com.axonactive.personalproject.service;

import com.axonactive.personalproject.controller.request.ApplicationFormRequest;
import com.axonactive.personalproject.entity.ApplicationForm;
import com.axonactive.personalproject.exception.EntityNotFoundException;
import com.axonactive.personalproject.service.dto.ApplicationFormDto;

import java.time.LocalDate;
import java.util.List;

public interface ApplicationFormService {
  List<ApplicationFormDto> findAll();

  ApplicationFormDto findById(Integer id) throws EntityNotFoundException;

  void deleteById(Integer id) throws EntityNotFoundException;

  ApplicationForm add(ApplicationFormRequest request) throws EntityNotFoundException;

  ApplicationForm convertRequestToEntity(ApplicationFormRequest request) throws EntityNotFoundException;

  ApplicationFormDto save(ApplicationFormRequest request) throws EntityNotFoundException;

  List<ApplicationFormDto> findBySubmittedDateBetween(String fromDate, String toDate);

//  List<ApplicationFormDto> findFirstByCandidateIdOrderBySubmittedDateDesc(Integer Id);

  List<ApplicationFormDto> findByHiringRequestHiringManagerId(Integer Id);

  ApplicationFormDto update(Integer id,ApplicationFormRequest updateForm) throws EntityNotFoundException;

  Double getSalary (Integer id) throws EntityNotFoundException;

  Boolean isValidHrOfficer (ApplicationForm applicationForm);

  Boolean isValidSubmittedDate(LocalDate date);
}
