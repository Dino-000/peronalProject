package com.axonactive.personalproject.service;

import com.axonactive.personalproject.controller.request.ApplicationFormRequest;
import com.axonactive.personalproject.entity.ApplicationForm;
import com.axonactive.personalproject.exception.EntityNotFoundException;
import com.axonactive.personalproject.service.dto.ApplicationFormDto;
import com.axonactive.personalproject.service.dto.CandidatePortfolioDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface ApplicationFormService {

  List<ApplicationFormDto> findAll();

  ApplicationFormDto findById(Integer id);

  void deleteById(Integer id);

  ApplicationForm add(ApplicationFormRequest request);

  ApplicationForm convertRequestToEntity(ApplicationFormRequest request);

  ApplicationFormDto save(ApplicationFormRequest request);

  List<ApplicationFormDto> findBySubmittedDateBetween(String fromDate, String toDate);

  List<ApplicationFormDto> findByHiringRequestHiringManagerId(Integer Id);

  ApplicationFormDto update(Integer id, ApplicationFormRequest updateForm);

  Double getSalary(Integer id);

  Boolean isValidSubmittedDate(LocalDate date);

  String addCv(Integer id, MultipartFile file) throws IOException;

  byte[] getCv(Integer id) throws EntityNotFoundException, IOException;

  CandidatePortfolioDto findPortfolio(Integer id);
}
