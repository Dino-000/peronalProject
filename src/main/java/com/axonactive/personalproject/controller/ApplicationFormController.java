package com.axonactive.personalproject.controller;

import com.axonactive.personalproject.controller.request.ApplicationFormRequest;
import com.axonactive.personalproject.entity.ApplicationForm;
import com.axonactive.personalproject.exception.ResourceNotFoundException;
import com.axonactive.personalproject.service.*;
import com.axonactive.personalproject.service.CandidateService;
import com.axonactive.personalproject.service.HiringRequestService;
import com.axonactive.personalproject.service.RecruitmentChanelService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = ApplicationFormController.PATH)
@RequiredArgsConstructor
public class ApplicationFormController {
  public static final String PATH = "/api/ApplicationForms";
  @Autowired ApplicationFormService applicationFormService;

  @Autowired CandidateService candidateService;
  @Autowired HiringRequestService hiringRequestService;
  @Autowired RecruitmentChanelService recruitmentChanelService;
  @Autowired
  EmployeeService employeeService;

  @GetMapping
  public ResponseEntity<List<ApplicationForm>> getAll() {
    return ResponseEntity.ok().body(applicationFormService.findAll());
  }

  @PostMapping
  public ResponseEntity<ApplicationForm> add(
      @RequestBody ApplicationFormRequest formRequest) {
    ApplicationForm newForm =applicationFormService.saveApplicationForm(
        new ApplicationForm(
            null,
            formRequest.getSubmittedDate(),
            formRequest.getNoticePeriods(),
            formRequest.getUrlCV(),
            formRequest.getSalaryExpectation(),
            candidateService.findById(formRequest.getCandidateId()).get(),
            hiringRequestService.findById(formRequest.getHiringRequestId()).get(),
            recruitmentChanelService.findById(formRequest.getRecruitmentChanelId()).get(),
            employeeService.findById(formRequest.getHrOfficerId()).get()
        ));

    return ResponseEntity.created(URI.create(PATH + "/" + newForm.getId())).body(newForm);
  }

  @PutMapping("/{id}")
    public  ResponseEntity<ApplicationForm> update(@PathVariable("id") Integer id,@RequestBody ApplicationFormRequest updatingRequest) throws ResourceNotFoundException {
      ApplicationForm updatingForm = applicationFormService.findById(id).orElseThrow(()->new ResourceNotFoundException("Can't not find Application Form with that id."));
      updatingForm.setSubmittedDate(updatingRequest.getSubmittedDate());
      updatingForm.setNoticePeriods(updatingRequest.getNoticePeriods());
      updatingForm.setUrlCV(updatingRequest.getUrlCV());
      updatingForm.setSalaryExpectation(updatingRequest.getSalaryExpectation());
      updatingForm.setCandidate(candidateService.findById(updatingRequest.getCandidateId()).get());
      updatingForm.setHiringRequest(hiringRequestService.findById(updatingRequest.getHiringRequestId()).get());
      updatingForm.setRecruitmentChanel(recruitmentChanelService.findById(updatingRequest.getRecruitmentChanelId()).get());
    updatingForm.setHrOfficer(employeeService.findById(updatingRequest.getHrOfficerId()).get());
    ApplicationForm updatedForm = applicationFormService.saveApplicationForm(updatingForm);
    return  ResponseEntity.created(URI.create(PATH+"/"+id)).body(updatedForm);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws ResourceNotFoundException {
    ApplicationForm foundForm = applicationFormService.findById(id).orElseThrow(()->new ResourceNotFoundException("Can't not find Application Form with that id."));
    applicationFormService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
