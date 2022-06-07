package com.axonactive.personalproject.service;

import com.axonactive.personalproject.entity.ApplicationForm;

import java.util.List;
import java.util.Optional;

public interface ApplicationFormService {
List<ApplicationForm> findAll();
Optional<ApplicationForm> findById(Integer id);

void deleteById(Integer id);

ApplicationForm saveApplicationForm(ApplicationForm applicationForm);



}