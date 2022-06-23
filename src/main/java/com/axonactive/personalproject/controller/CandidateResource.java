package com.axonactive.personalproject.controller;

import com.axonactive.personalproject.entity.Candidate;
import com.axonactive.personalproject.exception.EntityNotFoundException;
import com.axonactive.personalproject.service.CandidateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.net.URI;
import java.util.List;
import java.util.Set;
@Validated
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(CandidateResource.PATH)
public class CandidateResource {
  public static final String PATH = "api/candidates";
  @Autowired CandidateService candidateService;

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
  public ResponseEntity<Candidate> getById(@Valid @PathVariable("id") Integer id)
      throws EntityNotFoundException {

    //    try {
    return ResponseEntity.created(URI.create(PATH + "/" + id)).body(candidateService.findById(id));
    //    } catch (EntityNotFoundException e) {
    //      log.error("Can not find the candidate with given id.");
    //      throw e;
    //    }
  }

  @GetMapping("/gpa-edu-skill-seniority")
  public ResponseEntity<Set<Candidate>> findByGPAAndSkillSetAndEducationAndSeniority(
     @Valid @RequestParam("gpa") Double gpa,
     @Valid @RequestParam("skill") String skillSetName,
     @Valid @RequestParam("school") String schoolName,
     @Valid @RequestParam("seniority") String Seniority) {
    return ResponseEntity.ok()
        .body(
            candidateService.findByGPAAndSkillSetAndEducationAndSeniority(
                gpa, skillSetName, schoolName, Seniority));
  }

  @GetMapping("/job-type")
  public ResponseEntity<Set<Candidate>> findByExperiencedWithJobType(
     @Valid @RequestParam("type") String jobType) {
    return ResponseEntity.ok().body(candidateService.findByExperiencedWithJobType(jobType));
  }

  @GetMapping("/skill-salary")
  public ResponseEntity<Set<Candidate>> findBySalaryAndSkillSet(
      @Valid @RequestParam("salary") Double maxSalaryExpectation,
      @Valid @RequestParam("skill") @Pattern(regexp = "[a-z]{3}]") String SkillSetName) {
    return ResponseEntity.ok()
        .body(
            candidateService.findBySalaryExpectationLessThanAndCandidateSkillSet(
                maxSalaryExpectation, SkillSetName));
  }

  @GetMapping("/location-skill-seniority")
  public ResponseEntity<Set<Candidate>> findByLocationAndSkillSetAndSeniority(
     @Valid @RequestParam("location") String location,
     @Valid @RequestParam("skill") String skillSetName,
     @Valid @RequestParam("seniority") String Seniority) {
    return ResponseEntity.ok()
        .body(
            candidateService.findByLocationAndSkillSetAndSeniority(
                location, skillSetName, Seniority));
  }

  @GetMapping("/application-form/{id}/salary-expectation-over-budget")
  public ResponseEntity<Set<Candidate>> findBySalaryExpectationGreaterThanHiringRequestBudget(
    @Valid @PathVariable("id") Integer id) {
    return ResponseEntity.ok()
        .body(candidateService.findBySalaryExpectationGreaterThanHiringRequestBudget(id));
  }

  @GetMapping("/company")
  public ResponseEntity<Set<Candidate>> findByExperiencedACompany(
    @Valid @RequestParam("company") String companyName) {
    return ResponseEntity.ok()
        .body(candidateService.findByExperiencesInSpecificCompany(companyName));
  }

  @GetMapping("/education")
  public ResponseEntity<Set<Candidate>> findByEducation(@Valid @RequestParam("school") String schoolName) {
    return ResponseEntity.ok().body(candidateService.findByEducation(schoolName));
  }

  @GetMapping("/certification")
  public ResponseEntity<Set<Candidate>> findByCertification(@Valid @RequestParam("name") String name) {
    return ResponseEntity.ok().body(candidateService.findByCertification(name));
  }

  @PostMapping
  public ResponseEntity<Candidate> add(@Valid @RequestBody Candidate inputData) {
    Candidate newCandidate = candidateService.add(inputData);

    return ResponseEntity.created(URI.create(PATH + "/" + newCandidate.getId())).body(newCandidate);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Candidate> update(
     @Valid @PathVariable("id") Integer id,@Valid @RequestBody Candidate updateDetail)
      throws EntityNotFoundException {
    Candidate foundCandidate = null;
    foundCandidate = candidateService.update(updateDetail, id);
    return ResponseEntity.created(URI.create(PATH + "/" + id)).body(foundCandidate);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@Valid @PathVariable("id") Integer id)
      throws EntityNotFoundException {
    candidateService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
