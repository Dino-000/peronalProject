package com.axonactive.personalproject.service.serviceImpl;

import com.axonactive.personalproject.controller.request.ApplicationFormRequest;
import com.axonactive.personalproject.entity.ApplicationForm;
import com.axonactive.personalproject.entity.Employee;
import com.axonactive.personalproject.exception.BusinessConstraintException;
import com.axonactive.personalproject.exception.EntityNotFoundException;
import com.axonactive.personalproject.repository.ApplicationFormRepository;
import com.axonactive.personalproject.service.*;
import com.axonactive.personalproject.service.dto.ApplicationFormDto;
import com.axonactive.personalproject.service.dto.CandidatePortfolioDto;
import com.axonactive.personalproject.service.mapper.ApplicationFormMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class ApplicationFormServiceImpl implements ApplicationFormService {
  @Autowired ApplicationFormRepository applicationFormRepository;
  @Autowired CandidateService candidateService;
  @Autowired HiringRequestService hiringRequestService;
  @Autowired RecruitmentChanelService recruitmentChanelService;
  @Autowired EmployeeService employeeService;
  @Autowired CandidateCertificationService candidateCertificationService;
  @Autowired CandidateEducationService candidateEducationService;
  @Autowired CandidateSkillSetService candidateSkillSetService;
  @Autowired WorkingHistoryRecordSkillSetService workingHistoryRecordSkillSetService;

  @Override
  public CandidatePortfolioDto findPortfolio(Integer id) {
    return new CandidatePortfolioDto(
        candidateService.findById(id),
        candidateCertificationService.findByCandidateId(id),
        candidateEducationService.findByCandidateId(id),
        candidateSkillSetService.findByCandidateId(id),
        workingHistoryRecordSkillSetService.findByWorkingHistoryRecordCandidateId(id));
  }

  @Value("${cv.storage}")
  private String resourcePath;

  @Override
  public List<ApplicationFormDto> findAll() {
    return ApplicationFormMapper.INSTANCE.toDtos(applicationFormRepository.findAll());
  }

  @Override
  public ApplicationFormDto findById(Integer id) {
    log.info("Current application form id :" + id);
    ApplicationForm applicationForm =
        applicationFormRepository
            .findById(id)
            .orElseThrow(EntityNotFoundException::applicationFormNotFound);
    return ApplicationFormMapper.INSTANCE.toDto(applicationForm);
  }

  @Override
  public ApplicationForm add(ApplicationFormRequest request) {
    ApplicationForm applicationForm = convertRequestToEntity(request);
    return applicationForm;
  }

  @Override
  public ApplicationFormDto save(ApplicationFormRequest request) {
    request.setSubmittedDate(returnValidSubmittedDate(request.getSubmittedDate()));
    return ApplicationFormMapper.INSTANCE.toDto(
        applicationFormRepository.save(convertRequestToEntity(request)));
  }

  @Override
  public void deleteById(Integer id) {
    log.info("The given application from id : " + id);
    ApplicationForm foundForm =
        applicationFormRepository
            .findById(id)
            .orElseThrow(EntityNotFoundException::applicationFormNotFound);
    applicationFormRepository.deleteById(id);
  }

  @Override
  public ApplicationForm convertRequestToEntity(ApplicationFormRequest request) {
    log.info("Current candidate id :" + request.getCandidateId());
    log.info("Current hiring request id :" + request.getHiringRequestId());
    log.info("Current recruitment channel id :" + request.getRecruitmentChanelId());

    if (!isValidSubmittedDate(request.getSubmittedDate())) {
      log.info("The given submitted Date is: " + request.getSubmittedDate());
      throw BusinessConstraintException.invalidSubmittedDate();
    }
    return new ApplicationForm(
        null,
        returnValidSubmittedDate(request.getSubmittedDate()),
        request.getNoticePeriods(),
        request.getUrlCV(),
        request.getSalaryExpectation(),
        candidateService.findById(request.getCandidateId()),
        hiringRequestService.checkValidHiringRequestId(request.getHiringRequestId()),
        recruitmentChanelService.findById(request.getRecruitmentChanelId()),
        employeeService.checkValidHrOfficerId(request.getHrOfficerId()));
  }

  @Override
  public List<ApplicationFormDto> findBySubmittedDateBetween(LocalDate fromDate, LocalDate toDate) {

    return ApplicationFormMapper.INSTANCE.toDtos(
        applicationFormRepository.findBySubmittedDateBetween(fromDate, toDate));
  }

  @Override
  public List<ApplicationFormDto> findByHiringRequestHiringManagerId(Integer id) {
    log.info("The given Hiring Manager id:" + id);
    Employee foundHiringManager = employeeService.checkValidHiringManagerId(id);
    return ApplicationFormMapper.INSTANCE.toDtos(
        applicationFormRepository.findByHiringRequestHiringManagerId(id));
  }

  @Override
  public ApplicationFormDto update(Integer id, ApplicationFormRequest updateForm) {

    ApplicationForm updatingForm =
        applicationFormRepository
            .findById(id)
            .orElseThrow(EntityNotFoundException::applicationFormNotFound);
    updatingForm.setSubmittedDate(returnValidSubmittedDate(updateForm.getSubmittedDate()));
    updatingForm.setNoticePeriods(updateForm.getNoticePeriods());
    updatingForm.setUrlCV(updateForm.getUrlCV());
    updatingForm.setSalaryExpectation(updateForm.getSalaryExpectation());
    updatingForm.setCandidate(candidateService.findById(updateForm.getCandidateId()));
    updatingForm.setHiringRequest(
        hiringRequestService.checkValidHiringRequestId(updateForm.getHiringRequestId()));
    updatingForm.setRecruitmentChanel(
        recruitmentChanelService.findById(updateForm.getRecruitmentChanelId()));

    updatingForm.setHrOfficer(employeeService.checkValidHrOfficerId(updateForm.getHrOfficerId()));

    return ApplicationFormMapper.INSTANCE.toDto(applicationFormRepository.save(updatingForm));
  }

  public String addCv(Integer id, MultipartFile file) throws IOException {

    String pathDirectory = new File("").getAbsolutePath();

    String filePath =
        pathDirectory
            + File.separator
            + resourcePath
            + File.separator
            + "user"
            + id
            + "CV."
            + FilenameUtils.getExtension(file.getOriginalFilename());
    Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
    log.info(filePath);
    ApplicationForm updatingForm =
        applicationFormRepository
            .findById(id)
            .orElseThrow(EntityNotFoundException::applicationFormNotFound);
    updatingForm.setUrlCV(filePath);
    applicationFormRepository.save(updatingForm);
    String CvUrl = "localhost:8080/api/application-forms/" + id + "/cv";
    return CvUrl;
  }

  @Override
  public byte[] getCv(Integer id) throws IOException {
    log.info(
        "The application form id is : "
            + id
            + " and the file path is : "
            + findById(id).getUrlCV());
    return StreamUtils.copyToByteArray(
        Files.newInputStream(new File(findById(id).getUrlCV()).toPath()));
  }

  @Override
  public Double getSalary(Integer id) {
    log.info("Current application form id :" + id);
    return applicationFormRepository
        .findById(id)
        .orElseThrow(EntityNotFoundException::applicationFormNotFound)
        .getSalaryExpectation();
  }

  @Override
  public Boolean isValidSubmittedDate(LocalDate date) {
    return !LocalDate.now().isBefore(date);
  }

  public LocalDate returnValidSubmittedDate(LocalDate submittedDate) {
    if (!isValidSubmittedDate(submittedDate)) {
      log.info("The given submitted Date is: " + submittedDate);
      throw BusinessConstraintException.invalidSubmittedDate();
    }
    return submittedDate;
  }
}
