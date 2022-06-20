package com.axonactive.personalproject.service.serviceImpl;

import com.axonactive.personalproject.controller.request.ApplicationFormRequest;
import com.axonactive.personalproject.entity.ApplicationForm;
import com.axonactive.personalproject.exception.BusinessConstraintException;
import com.axonactive.personalproject.exception.EntityNotFoundException;
import com.axonactive.personalproject.repository.*;
import com.axonactive.personalproject.service.ApplicationFormService;
import com.axonactive.personalproject.service.dto.ApplicationFormDto;
import com.axonactive.personalproject.service.mapper.ApplicationFormMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
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
@RequiredArgsConstructor
public class ApplicationFormServiceImpl implements ApplicationFormService {
  @Autowired ApplicationFormRepository applicationFormRepository;
  @Autowired CandidateRepository candidateRepository;
  @Autowired HiringRequestRepository hiringRequestRepository;
  @Autowired RecruitmentChanelRepository recruitmentChanelRepository;
  @Autowired EmployeeRepository employeeRepository;

//  @Value("${cv.storage}")
//  private final String resourcePath;

  @Override
  public List<ApplicationFormDto> findAll() {
    return ApplicationFormMapper.INSTANCE.toDtos(applicationFormRepository.findAll());
  }

  @Override
  public ApplicationFormDto findById(Integer id) throws EntityNotFoundException {
    ApplicationForm applicationForm =
        applicationFormRepository
            .findById(id)
            .orElseThrow(EntityNotFoundException::applicationFormNotFound);
    return ApplicationFormMapper.INSTANCE.toDto(applicationForm);
  }

  @Override
  public ApplicationForm add(ApplicationFormRequest request) throws EntityNotFoundException {
    ApplicationForm applicationForm = convertRequestToEntity(request);
    if (!isValidHrOfficer(applicationForm)) {
      log.info(
          "The HrOfficer's Department: "
              + applicationForm.getHrOfficer().getDepartment().getName());
      throw BusinessConstraintException.invalidHrOfficer();
    }
    if (!isValidSubmittedDate(request.getSubmittedDate())) {
      log.info("The submitted date is: " + request.getSubmittedDate());
      throw BusinessConstraintException.invalidSubmittedDate();
    }
    return applicationForm;
  }

  @Override
  public ApplicationFormDto save(ApplicationFormRequest request) throws EntityNotFoundException {
    return ApplicationFormMapper.INSTANCE.toDto(
        applicationFormRepository.save(convertRequestToEntity(request)));
  }

  @Override
  public void deleteById(Integer id) throws EntityNotFoundException {
    ApplicationForm foundForm =
        applicationFormRepository
            .findById(id)
            .orElseThrow(EntityNotFoundException::applicationFormNotFound);
    applicationFormRepository.deleteById(id);
  }

  @Override
  public ApplicationForm convertRequestToEntity(ApplicationFormRequest request)
      throws EntityNotFoundException {
    return new ApplicationForm(
        null,
        request.getSubmittedDate(),
        request.getNoticePeriods(),
        request.getUrlCV(),
        request.getSalaryExpectation(),
        candidateRepository
            .findById(request.getCandidateId())
            .orElseThrow(EntityNotFoundException::candidateNotFound),
        hiringRequestRepository
            .findById(request.getHiringRequestId())
            .orElseThrow(EntityNotFoundException::hiringRequestNotFound),
        recruitmentChanelRepository
            .findById(request.getRecruitmentChanelId())
            .orElseThrow(EntityNotFoundException::recruitmentChannelNotFound),
        employeeRepository
            .findById(request.getHrOfficerId())
            .orElseThrow(EntityNotFoundException::employeeNotFound));
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
      throws EntityNotFoundException {
    ApplicationForm updatingForm =
        applicationFormRepository
            .findById(id)
            .orElseThrow(EntityNotFoundException::applicationFormNotFound);
    updatingForm.setSubmittedDate(updateForm.getSubmittedDate());
    updatingForm.setNoticePeriods(updateForm.getNoticePeriods());
    updatingForm.setUrlCV(updateForm.getUrlCV());
    updatingForm.setSalaryExpectation(updateForm.getSalaryExpectation());
    updatingForm.setCandidate(
        candidateRepository
            .findById(updateForm.getCandidateId())
            .orElseThrow(EntityNotFoundException::candidateNotFound));
    updatingForm.setHiringRequest(
        hiringRequestRepository
            .findById(updateForm.getHiringRequestId())
            .orElseThrow(EntityNotFoundException::hiringRequestNotFound));
    updatingForm.setRecruitmentChanel(
        recruitmentChanelRepository
            .findById(updateForm.getRecruitmentChanelId())
            .orElseThrow(EntityNotFoundException::recruitmentChannelNotFound));
    updatingForm.setHrOfficer(
        employeeRepository
            .findById(updateForm.getHrOfficerId())
            .orElseThrow(EntityNotFoundException::employeeNotFound));

    if (!isValidHrOfficer(updatingForm)) {
      log.info(
          "The HrOfficer's Department: " + updatingForm.getHrOfficer().getDepartment().getName());
      throw BusinessConstraintException.invalidHrOfficer();
    }
    if (!isValidSubmittedDate(updatingForm.getSubmittedDate())) {
      log.info("The Summited Date: " + updatingForm.getSubmittedDate());
      throw BusinessConstraintException.invalidSubmittedDate();
    }
    return ApplicationFormMapper.INSTANCE.toDto(applicationFormRepository.save(updatingForm));
  }


  public String addCv(Integer id, MultipartFile file) throws IOException {


    //            String Path_directory =
    // "/Users/dino/Downloads/personalproject/src/main/resources/static/Cv";
//    String Path_directory = new ClassPathResource("static/Cv/").getFile().getAbsolutePath();
//    String CvUrl = Path_directory + File.separator + file.getOriginalFilename();
    String pathDirectory = new File("").getAbsolutePath();
    String CvUrl =  File.separator + file.getOriginalFilename();
    String filePath= pathDirectory+File.separator+"src/main/resources/cv"+CvUrl;
    Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);

    return CvUrl;
  }


  @Override
  public Double getSalary(Integer id) {
    return applicationFormRepository
        .findById(id)
        .orElseThrow(EntityNotFoundException::applicationFormNotFound)
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
