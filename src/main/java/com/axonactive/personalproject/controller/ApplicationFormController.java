package com.axonactive.personalproject.controller;

import com.axonactive.personalproject.controller.request.ApplicationFormRequest;
import com.axonactive.personalproject.entity.ApplicationForm;
import com.axonactive.personalproject.service.ApplicationFormService;
import com.axonactive.personalproject.service.CandidateService;
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
    public static final String PATH="/api/ApplicationForms";
@Autowired
    ApplicationFormService applicationFormService;

@Autowired
    CandidateService candidateService
@GetMapping
    public ResponseEntity<List<ApplicationForm>> getAll() {
    return  ResponseEntity.ok().body(applicationFormService.findAll());
}

@PostMapping
    public ResponseEntity<ApplicationForm> add(@RequestBody ApplicationFormRequest applicationFormRequest){
    ApplicationForm newForm = new ApplicationForm(null,
            applicationFormRequest.getSubmittedDate(),
            applicationFormRequest.getNoticePeriods(),
            applicationFormRequest.getUrlCV(),
            applicationFormRequest.getSalaryExpectation(),
            candidateService.findById(applicationFormRequest.getCandidateId()),
            )

    return ResponseEntity.created(URI.create())
}
}