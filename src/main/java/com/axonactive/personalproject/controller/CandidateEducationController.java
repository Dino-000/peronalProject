package com.axonactive.personalproject.controller;

import com.axonactive.personalproject.controller.request.CandidateEducationRequest;
import com.axonactive.personalproject.entity.CandidateEducation;
import com.axonactive.personalproject.exception.ResourceNotFoundException;
import com.axonactive.personalproject.service.CandidateEducationService;
import com.axonactive.personalproject.service.CandidateService;
import com.axonactive.personalproject.service.EducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(CandidateEducationController.PATH)
public class CandidateEducationController {
    public static final String PATH ="api/CandidateEducationEducation";
    @Autowired
    CandidateEducationService candidateEducationService;
    @Autowired
    EducationService educationService;
    @Autowired
    CandidateService candidateService;

    @GetMapping
    public ResponseEntity<List<CandidateEducation>> getAll() {
        return ResponseEntity.ok().body(candidateEducationService.findAll());
    }

    @PostMapping
    public ResponseEntity<CandidateEducation> add(
            @RequestBody CandidateEducationRequest inputData) {
        CandidateEducation newCandidateEducation = candidateEducationService.saveEducationList(new CandidateEducation(null,
                candidateService.findById(inputData.getCandidateId()).get(),
                educationService.findById(inputData.getEducationId()).get()
        ));

        return ResponseEntity.created(URI.create(PATH + "/" + newCandidateEducation.getId())).body(newCandidateEducation);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<CandidateEducation> update(@PathVariable("id") Integer id, @RequestBody CandidateEducationRequest inputData) throws ResourceNotFoundException {
        CandidateEducation updatingCandidateEducation = candidateEducationService.findById(id).orElseThrow(()->new ResourceNotFoundException("Can't not find Application Form with that id."));
        updatingCandidateEducation.setCandidate(candidateService.findById(inputData.getCandidateId()).get());
        updatingCandidateEducation.setEducation(educationService.findById(inputData.getEducationId()).get());
        CandidateEducation updatedCandidateEducation = candidateEducationService.saveEducationList(updatingCandidateEducation);
        return  ResponseEntity.created(URI.create(PATH+"/"+id)).body(updatedCandidateEducation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        CandidateEducation deletingCandidateEducation = candidateEducationService.findById(id).orElseThrow(()->new ResourceNotFoundException("Can't not find Application Form with that id."));
        candidateEducationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
