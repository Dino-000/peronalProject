package com.axonactive.personalproject.controller;

import com.axonactive.personalproject.controller.request.CandidateEducationRequest;
import com.axonactive.personalproject.entity.CandidateEducation;
import com.axonactive.personalproject.exception.ResourceNotFoundException;
import com.axonactive.personalproject.service.CandidateEducationService;
import com.axonactive.personalproject.service.CandidateService;
import com.axonactive.personalproject.service.EducationService;
import com.axonactive.personalproject.service.dto.CandidateEducationDto;
import com.axonactive.personalproject.service.mapper.CandidateEducationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(CandidateEducationResource.PATH)
public class CandidateEducationResource {
  public static final String PATH = "api/candidate-educations";
  @Autowired CandidateEducationService candidateEducationService;
  @Autowired EducationService educationService;
  @Autowired CandidateService candidateService;

  @GetMapping
  public ResponseEntity<List<CandidateEducationDto>> getAll() {
    return ResponseEntity.ok()
        .body(CandidateEducationMapper.INSTANCE.toDtos(candidateEducationService.findAll()));
  }

  @GetMapping("/{id}")
  public ResponseEntity<CandidateEducationDto> getById (@PathVariable("id") Integer id) throws ResourceNotFoundException {
    CandidateEducation candidateEducation =
            candidateEducationService
                    .findById(id)
                    .orElseThrow(
                            () ->
                                    new ResourceNotFoundException("Can't not find Candidate Education with that id."));
    return ResponseEntity.created(URI.create(PATH+"/"+id)).body(CandidateEducationMapper.INSTANCE.toDto(candidateEducation));
  }
  @PostMapping
  public ResponseEntity<CandidateEducationDto> add(
      @RequestBody CandidateEducationRequest inputData) {
    CandidateEducation newCandidateEducation =
        candidateEducationService.saveEducationList(
            new CandidateEducation(
                null,
                candidateService.findById(inputData.getCandidateId()).get(),
                educationService.findById(inputData.getEducationId()).get(),
                inputData.getGraduationYear()));

    return ResponseEntity.created(URI.create(PATH + "/" + newCandidateEducation.getId()))
        .body(CandidateEducationMapper.INSTANCE.toDto(newCandidateEducation));
  }

  @PutMapping("/{id}")
  public ResponseEntity<CandidateEducationDto> update(
      @PathVariable("id") Integer id, @RequestBody CandidateEducationRequest inputData)
      throws ResourceNotFoundException {
    CandidateEducation updatingCandidateEducation =
        candidateEducationService
            .findById(id)
            .orElseThrow(
                () ->
                    new ResourceNotFoundException("Can't not find Candidate Education with that id."));
    updatingCandidateEducation.setCandidate(
        candidateService.findById(inputData.getCandidateId()).get());
    updatingCandidateEducation.setEducation(
        educationService.findById(inputData.getEducationId()).get());
    updatingCandidateEducation.setGraduationYear(inputData.getGraduationYear());
    CandidateEducation updatedCandidateEducation =
        candidateEducationService.saveEducationList(updatingCandidateEducation);
    return ResponseEntity.created(URI.create(PATH + "/" + id))
        .body(CandidateEducationMapper.INSTANCE.toDto(updatedCandidateEducation));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Integer id)
      throws ResourceNotFoundException {
    CandidateEducation deletingCandidateEducation =
        candidateEducationService
            .findById(id)
            .orElseThrow(
                () ->
                    new ResourceNotFoundException("Can't not findCandidate Education with that id."));
    candidateEducationService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
