package com.axonactive.personalproject.controller;

import com.axonactive.personalproject.entity.Candidate;
import com.axonactive.personalproject.exception.ResourceNotFoundException;
import com.axonactive.personalproject.service.*;
import com.axonactive.personalproject.service.dto.CandidatePortfolioDto;
import com.axonactive.personalproject.service.mapper.WorkingHistoryRecordMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping(CandidateResource.PATH)
public class CandidateResource {
  public static final String PATH = "api/Candidates";
  @Autowired
  CandidateService candidateService;
  @Autowired
  CertificationService certificationService;
  @Autowired
  EducationService educationService;
  @Autowired
  SkillSetService skillSetService;
  @Autowired
  WorkingHistoryRecordService workingHistoryRecordService;

  @GetMapping
  public ResponseEntity<List<Candidate>> getAll() {
    return ResponseEntity.ok().body(candidateService.findAll());
  }

  @GetMapping("/team-size")
  public ResponseEntity<Set<Candidate>> findByExperiencesInTeamSize(
      @RequestParam("size") Integer teamSize) {
    return ResponseEntity.ok().body(candidateService.findByExperiencedTeamSizeLargerThan(teamSize));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Candidate> getById(@PathVariable("id") Integer id)
      throws ResourceNotFoundException {
    Candidate candidate =
        candidateService
            .findById(id)
            .orElseThrow(
                () -> new ResourceNotFoundException("Can't not find Candidate with that id."));
    return ResponseEntity.created(URI.create(PATH + "/" + candidate.getId())).body(candidate);
  }

  @GetMapping("/gpa-edu-skill-seniority")
  public ResponseEntity<Set<Candidate>> findByGPAAndSkillSetAndEducationAndSeniority(
      @RequestParam("gpa") Double gpa,
      @RequestParam("skill") String skillSetName,
      @RequestParam("school") String schoolName,
      @RequestParam("seniority") String Seniority) {
    return ResponseEntity.ok()
        .body(
            candidateService.findByGPAAndSkillSetAndEducationAndSeniority(
                gpa, skillSetName, schoolName, Seniority));
  }

  @GetMapping("/job-type")
  public ResponseEntity<Set<Candidate>> findByExperiencedWithJobType(
      @RequestParam("type") String jobType) {
    return ResponseEntity.ok().body(candidateService.findByExperiencedWithJobType(jobType));
  }

  @GetMapping("/skill-salary")
  public ResponseEntity<Set<Candidate>> findBySalaryAndSkillSet(
      @RequestParam("salary") Double maxSalaryExpectation,
      @RequestParam("skill") String SkillSetName) {
    return ResponseEntity.ok()
        .body(
            candidateService.findBySalaryExpectationLessThanAndCandidateSkillSet(
                maxSalaryExpectation, SkillSetName));
  }

  @GetMapping("/location-skill-seniority")
  public ResponseEntity<Set<Candidate>> findByLocationAndSkillSetAndSeniority(
      @RequestParam("location") String location,
      @RequestParam("skill") String skillSetName,
      @RequestParam("seniority") String Seniority) {
    return ResponseEntity.ok()
        .body(
            candidateService.findByLocationAndSkillSetAndSeniority(
                location, skillSetName, Seniority));
  }

  @GetMapping("/salary-expectation-over-budget")
  public ResponseEntity<Set<Candidate>> findBySalaryExpectationGreaterThanHiringRequestBudget() {
    return ResponseEntity.ok()
        .body(candidateService.findBySalaryExpectationGreaterThanHiringRequestBudget());
  }

  @GetMapping("/company")
  public ResponseEntity<Set<Candidate>> findByExperiencedACompany(
      @RequestParam("company") String companyName) {
    return ResponseEntity.ok()
        .body(candidateService.findByExperiencesInSpecificCompany(companyName));
  }

  @GetMapping("/education")
  public ResponseEntity<Set<Candidate>> findByEducation(
      @RequestParam("school") String schoolName) {
    return ResponseEntity.ok().body(candidateService.findByEducation(schoolName));
  }

  @GetMapping("/certification")
  public ResponseEntity<Set<Candidate>> findByCertification(@RequestParam("name")String name) {
    return ResponseEntity.ok().body(candidateService.findByCertification(name));
  }

  @GetMapping("/{id}/portfolio")
  public ResponseEntity<CandidatePortfolioDto> findPortfolio(@PathVariable("id")Integer id) throws ResourceNotFoundException {
    Candidate foundCandidate = candidateService.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Can't not find Candidate with that id."));
    System.out.println(foundCandidate);
    CandidatePortfolioDto candidatePortfolioDto =
            new CandidatePortfolioDto(foundCandidate,
                    certificationService.findByCandidateId(id),
//                    null,
                    educationService.findByCandidateId(id),
//                    null,
                    skillSetService.findByCandidateId(id),
//                    null,
                    WorkingHistoryRecordMapper.INSTANCE.toDtos(workingHistoryRecordService.findByCandidateId(id))

            );
    return ResponseEntity.ok().body(candidatePortfolioDto);
  }


    @PostMapping
  public ResponseEntity<Candidate> add(@RequestBody Candidate inputData) {
    Candidate newCandidate = candidateService.saveCandidate(inputData);

    return ResponseEntity.created(URI.create(PATH + "/" + newCandidate.getId())).body(newCandidate);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Candidate> update(
      @PathVariable("id") Integer id, @RequestBody Candidate updateDetail)
      throws ResourceNotFoundException {
    Candidate updatingCandidate =
        candidateService
            .findById(id)
            .orElseThrow(
                () -> new ResourceNotFoundException("Can't not find Candidate with that id."));
    updatingCandidate.setName(updateDetail.getName());
    updatingCandidate.setDateOfBirth(updateDetail.getDateOfBirth());
    updatingCandidate.setLocation(updateDetail.getLocation());
    updatingCandidate.setOccupation(updateDetail.getOccupation());
    updatingCandidate.setSeniority(updateDetail.getSeniority());
    updatingCandidate.setGpa(updateDetail.getGpa());
    Candidate updatedCandidate = candidateService.saveCandidate(updatingCandidate);
    return ResponseEntity.created(URI.create(PATH + "/" + id)).body(updatedCandidate);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Integer id)
      throws ResourceNotFoundException {
    Candidate deletingCandidate =
        candidateService
            .findById(id)
            .orElseThrow(
                () -> new ResourceNotFoundException("Can't not find Candidate with that id."));
    candidateService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
