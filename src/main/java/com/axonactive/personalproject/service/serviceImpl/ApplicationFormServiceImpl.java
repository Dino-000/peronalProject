package com.axonactive.personalproject.service.serviceImpl;

import com.axonactive.personalproject.controller.request.ApplicationFormRequest;
import com.axonactive.personalproject.entity.ApplicationForm;
import com.axonactive.personalproject.exception.BusinessConstraintException;
import com.axonactive.personalproject.exception.ResourceNotFoundException;
import com.axonactive.personalproject.repository.*;
import com.axonactive.personalproject.service.ApplicationFormService;
import com.axonactive.personalproject.service.dto.ApplicationFormDto;
import com.axonactive.personalproject.service.mapper.ApplicationFormMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApplicationFormServiceImpl implements ApplicationFormService {
  @Autowired ApplicationFormRepository applicationFormRepository;
  @Autowired CandidateRepository candidateRepository;
  @Autowired HiringRequestRepository hiringRequestRepository;
  @Autowired RecruitmentChanelRepository recruitmentChanelRepository;
  @Autowired EmployeeRepository employeeRepository;

  @Override
  public List<ApplicationFormDto> findAll() {
    return ApplicationFormMapper.INSTANCE.toDtos(applicationFormRepository.findAll());
  }

  @Override
  public ApplicationFormDto findById(Integer id) throws ResourceNotFoundException {
    ApplicationForm applicationForm =
        applicationFormRepository
            .findById(id)
            .orElseThrow(ResourceNotFoundException::applicationFormNotFound);
    return ApplicationFormMapper.INSTANCE.toDto(applicationForm);
  }

  @Override
  public ApplicationForm add(ApplicationFormRequest request) throws ResourceNotFoundException {
    ApplicationForm applicationForm = convertRequestToEntity(request);
    if (!isValidHrOfficer(applicationForm)) {
      log.info(
          "The HrOfficer's Department: "
              + applicationForm.getHrOfficer().getDepartment().getName());
      throw BusinessConstraintException.invalidHrOfficer();
    } else if (!isValidSubmittedDate(request.getSubmittedDate())) {
      log.info("The submitted date is: " + request.getSubmittedDate());
      throw new IllegalArgumentException("The Submitted Date Is After Now.");
    }
    return applicationForm;
  }

  @Override
  public ApplicationFormDto save(ApplicationFormRequest request) throws ResourceNotFoundException {
    return ApplicationFormMapper.INSTANCE.toDto(
        applicationFormRepository.save(convertRequestToEntity(request)));
  }

  @Override
  public void deleteById(Integer id) throws ResourceNotFoundException {
    ApplicationForm foundForm =
        applicationFormRepository
            .findById(id)
            .orElseThrow(ResourceNotFoundException::applicationFormNotFound);
    applicationFormRepository.deleteById(id);
  }

  @Override
  public ApplicationForm convertRequestToEntity(ApplicationFormRequest request)
      throws ResourceNotFoundException {
    return new ApplicationForm(
        null,
        request.getSubmittedDate(),
        request.getNoticePeriods(),
        request.getUrlCV(),
        request.getSalaryExpectation(),
        candidateRepository
            .findById(request.getCandidateId())
            .orElseThrow(ResourceNotFoundException::candidateNotFound),
        hiringRequestRepository
            .findById(request.getHiringRequestId())
            .orElseThrow(ResourceNotFoundException::hiringRequestNotFound),
        recruitmentChanelRepository
            .findById(request.getRecruitmentChanelId())
            .orElseThrow(ResourceNotFoundException::recruitmentChannelNotFound),
        employeeRepository
            .findById(request.getHrOfficerId())
            .orElseThrow(ResourceNotFoundException::employeeNotFound));
  }

  @Override
  public List<ApplicationFormDto> findBySubmittedDateBetween(String fromDate, String toDate) {
    LocalDate beginDay = LocalDate.parse(fromDate);
    LocalDate untilDay = LocalDate.parse(toDate);
    return ApplicationFormMapper.INSTANCE.toDtos(
        applicationFormRepository.findBySubmittedDateBetween(beginDay, untilDay));
  }

  @Override
  public List<ApplicationFormDto> findByHiringRequestHiringManagerId(Integer Id) {
    return ApplicationFormMapper.INSTANCE.toDtos(
        applicationFormRepository.findByHiringRequestHiringManagerId(Id));
  }

  @Override
  public ApplicationFormDto update(Integer id, ApplicationFormRequest updateForm)
      throws ResourceNotFoundException {
    ApplicationForm updatingForm =
        applicationFormRepository
            .findById(id)
            .orElseThrow(ResourceNotFoundException::applicationFormNotFound);
    updatingForm.setSubmittedDate(updateForm.getSubmittedDate());
    updatingForm.setNoticePeriods(updateForm.getNoticePeriods());
    updatingForm.setUrlCV(updateForm.getUrlCV());
    updatingForm.setSalaryExpectation(updateForm.getSalaryExpectation());
    updatingForm.setCandidate(
        candidateRepository
            .findById(updateForm.getCandidateId())
            .orElseThrow(ResourceNotFoundException::candidateNotFound));
    updatingForm.setHiringRequest(
        hiringRequestRepository
            .findById(updateForm.getHiringRequestId())
            .orElseThrow(ResourceNotFoundException::hiringRequestNotFound));
    updatingForm.setRecruitmentChanel(
        recruitmentChanelRepository
            .findById(updateForm.getRecruitmentChanelId())
            .orElseThrow(ResourceNotFoundException::recruitmentChannelNotFound));
    updatingForm.setHrOfficer(
        employeeRepository
            .findById(updateForm.getHrOfficerId())
            .orElseThrow(ResourceNotFoundException::employeeNotFound));

    if (!isValidHrOfficer(updatingForm)) {
      log.info(
          "The HrOfficer's Department: " + updatingForm.getHrOfficer().getDepartment().getName());
      throw BusinessConstraintException.invalidHrOfficer();
    }
      return ApplicationFormMapper.INSTANCE.toDto(applicationFormRepository.save(updatingForm));

  }

  @Override
  public Double getSalary(Integer id) throws ResourceNotFoundException {
    return applicationFormRepository
        .findById(id)
        .orElseThrow(ResourceNotFoundException::applicationFormNotFound)
        .getSalaryExpectation();
  }

  @Override
  public Boolean isValidHrOfficer(ApplicationForm applicationForm) {
    return applicationForm.getHrOfficer().getDepartment().getName().equalsIgnoreCase("hr");
  }

  @Override
  public Boolean isValidSubmittedDate(LocalDate date) {
    return !LocalDate.now().isBefore(date);
  }
}
