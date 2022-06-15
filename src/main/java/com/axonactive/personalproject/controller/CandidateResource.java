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

  @GetMapping("/FindByExperiencesInTeamSize")
  public ResponseEntity<Set<Candidate>> findByExperiencesInTeamSize(
      @RequestParam("teamSize") Integer teamSize) {
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

  @GetMapping("/FindByGPAEduSkillAndSeniority")
  public ResponseEntity<Set<Candidate>> findByGPAAndSkillSetAndEducationAndSeniority(
      @RequestParam("GPA") Double gpa,
      @RequestParam("Skill") String skillSetName,
      @RequestParam("School") String schoolName,
      @RequestParam("Seniority") String Seniority) {
    return ResponseEntity.ok()
        .body(
            candidateService.findByGPAAndSkillSetAndEducationAndSeniority(
                gpa, skillSetName, schoolName, Seniority));
  }

  @GetMapping("/FindByExperiencedWithJobType")
  public ResponseEntity<Set<Candidate>> findByExperiencedWithJobType(
      @RequestParam("Type") String jobType) {
    return ResponseEntity.ok().body(candidateService.findByExperiencedWithJobType(jobType));
  }

  @GetMapping("/FindBySkillSetAndMaxSalary")
  public ResponseEntity<Set<Candidate>> findBySalaryAndSkillSet(
      @RequestParam("maxSalary") Double maxSalaryExpectation,
      @RequestParam("skillSet") String SkillSetName) {
    return ResponseEntity.ok()
        .body(
            candidateService.findBySalaryExpectationLessThanAndCandidateSkillSet(
                maxSalaryExpectation, SkillSetName));
  }

  @GetMapping("/findByLocationAndSkillSetAndSeniority")
  public ResponseEntity<Set<Candidate>> findByLocationAndSkillSetAndSeniority(
      @RequestParam("Location") String location,
      @RequestParam("SkillSet") String skillSetName,
      @RequestParam("Seniority") String Seniority) {
    return ResponseEntity.ok()
        .body(
            candidateService.findByLocationAndSkillSetAndSeniority(
                location, skillSetName, Seniority));
  }

  @GetMapping("/findByExpectationGreaterThanBudget")
  public ResponseEntity<Set<Candidate>> findBySalaryExpectationGreaterThanHiringRequestBudget() {
    return ResponseEntity.ok()
        .body(candidateService.findBySalaryExpectationGreaterThanHiringRequestBudget());
  }

  @GetMapping("/findByExperiencedACompany")
  public ResponseEntity<Set<Candidate>> findByExperiencedACompany(
      @RequestParam("CompanyName") String companyName) {
    return ResponseEntity.ok()
        .body(candidateService.findByExperiencesInSpecificCompany(companyName));
  }

  @GetMapping("/findByEducation")
  public ResponseEntity<Set<Candidate>> findByEducation(
      @RequestParam("SchoolName") String schoolName) {
    return ResponseEntity.ok().body(candidateService.findByEducation(schoolName));
  }

  @GetMapping("/FindByCertification")
  public ResponseEntity<Set<Candidate>> findByCertification(@RequestParam("name")String name) {
    return ResponseEntity.ok().body(candidateService.findByCertification(name));
  }

  @GetMapping("/{id}/FindPortfolio")
  public ResponseEntity<CandidatePortfolioDto> findPortfolio(@PathVariable("id")Integer id) throws ResourceNotFoundException {
    Candidate foundCandidate = candidateService.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Can't not find Candidate with that id."));
    CandidatePortfolioDto candidatePortfolioDto =
            new CandidatePortfolioDto(foundCandidate,
                    certificationService.findByCandidateId(id),
                    educationService.findByCandidateId(id),
                    skillSetService.findByCandidateId(id),
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
