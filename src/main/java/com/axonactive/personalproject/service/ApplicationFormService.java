package com.axonactive.personalproject.service;

import com.axonactive.personalproject.entity.ApplicationForm;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ApplicationFormService {
List<ApplicationForm> findAll();
Optional<ApplicationForm> findById(Integer id);

void deleteById(Integer id);

ApplicationForm saveApplicationForm(ApplicationForm applicationForm);

List<ApplicationForm> findBySubmittedDateBetween(LocalDate fromDate,LocalDate untilDate);

    List<ApplicationForm> findFirstByCandidateIdOrderBySubmittedDateDesc(Integer Id);

    List<ApplicationForm> findByHiringRequestHiringManagerId(Integer Id);


}