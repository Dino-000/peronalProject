package com.axonactive.personalproject.service.serviceImpl;

import com.axonactive.personalproject.controller.request.ApplicationFormRequest;
import com.axonactive.personalproject.entity.ApplicationForm;
import com.axonactive.personalproject.exception.ResourceNotFoundException;
import com.axonactive.personalproject.repository.*;
import com.axonactive.personalproject.service.ApplicationFormService;
import com.axonactive.personalproject.service.dto.ApplicationFormDto;
import com.axonactive.personalproject.service.mapper.ApplicationFormMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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
            .orElseThrow(
                () -> new ResourceNotFoundException("Can't not find Application Form with that id."));
    return ApplicationFormMapper.INSTANCE.toDto(applicationForm);
  }

  @Override
  public ApplicationForm add(ApplicationFormRequest request) throws ResourceNotFoundException {
    return applicationFormRepository.save(convertRequestToEntity(request));
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
            .orElseThrow(
                () -> new ResourceNotFoundException("Can't not find Application Form with that id."));
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
            .orElseThrow(
                () -> new ResourceNotFoundException("Can't not find candidate with that id.")),
        hiringRequestRepository
            .findById(request.getHiringRequestId())
            .orElseThrow(
                () -> new ResourceNotFoundException("Can't not find hiring request with that id.")),
        recruitmentChanelRepository
            .findById(request.getRecruitmentChanelId())
            .orElseThrow(
                () -> new ResourceNotFoundException(
                        "Can't not find recruitment chanel with that id.")),
        employeeRepository
            .findById(request.getHrOfficerId())
            .orElseThrow(
                () -> new ResourceNotFoundException("Can't not find employee with that id.")));
  }

  @Override
  public List<ApplicationFormDto> findBySubmittedDateBetween(String fromDate, String toDate) {
    LocalDate beginDay = LocalDate.parse(fromDate);
    LocalDate untilDay = LocalDate.parse(toDate);
    return ApplicationFormMapper.INSTANCE.toDtos(
        applicationFormRepository.findBySubmittedDateBetween(beginDay, untilDay));
  }
  // no controller
  //    @Override
  //    public List<ApplicationFormDto> findFirstByCandidateIdOrderBySubmittedDateDesc(Integer Id) {
  //        return applicationFormRepository.findFirstByCandidateIdOrderBySubmittedDateDesc(Id);
  //    }

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
            .orElseThrow(
                () ->
                    new ResourceNotFoundException("Can't not find Application Form with that id."));
    ;
    updatingForm.setSubmittedDate(updateForm.getSubmittedDate());
    updatingForm.setNoticePeriods(updateForm.getNoticePeriods());
    updatingForm.setUrlCV(updateForm.getUrlCV());
    updatingForm.setSalaryExpectation(updateForm.getSalaryExpectation());
    updatingForm.setCandidate(
        candidateRepository
            .findById(updateForm.getCandidateId())
            .orElseThrow(
                () -> new ResourceNotFoundException("Can't not find candidate with that id.")));
    updatingForm.setHiringRequest(
        hiringRequestRepository
            .findById(updateForm.getHiringRequestId())
            .orElseThrow(
                () ->
                    new ResourceNotFoundException("Can't not find hiring request with that id.")));
    updatingForm.setRecruitmentChanel(
        recruitmentChanelRepository
            .findById(updateForm.getRecruitmentChanelId())
            .orElseThrow(
                () ->
                    new ResourceNotFoundException(
                        "Can't not find recruitment chanel with that id.")));
    updatingForm.setHrOfficer(
        employeeRepository
            .findById(updateForm.getHrOfficerId())
            .orElseThrow(
                () -> new ResourceNotFoundException("Can't not find employee with that id.")));

    return ApplicationFormMapper.INSTANCE.toDto(applicationFormRepository.save(updatingForm));
  }

  @Override
  public Double getSalary(Integer id) throws ResourceNotFoundException {
    return applicationFormRepository
        .findById(id)
        .orElseThrow(
            () -> new ResourceNotFoundException("Can't not find Application Form with that id."))
        .getSalaryExpectation();
  }
}
