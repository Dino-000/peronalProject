package com.axonactive.personalproject.controller;

import com.axonactive.personalproject.entity.Candidate;
import com.axonactive.personalproject.exception.EntityNotFoundException;
import com.axonactive.personalproject.service.CandidateService;
import com.axonactive.personalproject.service.dto.CandidatePortfolioDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Set;

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
  public ResponseEntity<Candidate> getById(@PathVariable("id") Integer id)
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

  @GetMapping("/application-form/{id}/salary-expectation-over-budget")
  public ResponseEntity<Set<Candidate>> findBySalaryExpectationGreaterThanHiringRequestBudget(
      @PathVariable("id") Integer id) {
    return ResponseEntity.ok()
        .body(candidateService.findBySalaryExpectationGreaterThanHiringRequestBudget(id));
  }

  @GetMapping("/company")
  public ResponseEntity<Set<Candidate>> findByExperiencedACompany(
      @RequestParam("company") String companyName) {
    return ResponseEntity.ok()
        .body(candidateService.findByExperiencesInSpecificCompany(companyName));
  }

  @GetMapping("/education")
  public ResponseEntity<Set<Candidate>> findByEducation(@RequestParam("school") String schoolName) {
    return ResponseEntity.ok().body(candidateService.findByEducation(schoolName));
  }

  @GetMapping("/certification")
  public ResponseEntity<Set<Candidate>> findByCertification(@RequestParam("name") String name) {
    return ResponseEntity.ok().body(candidateService.findByCertification(name));
  }

  @GetMapping("/{id}/portfolio")
  public ResponseEntity<CandidatePortfolioDto> findPortfolio(@PathVariable("id") Integer id)
      throws EntityNotFoundException {
    return ResponseEntity.ok().body(candidateService.findPortfolio(id));
  }

  @PostMapping
  public ResponseEntity<Candidate> add(@RequestBody Candidate inputData) {
    Candidate newCandidate = candidateService.add(inputData);

    return ResponseEntity.created(URI.create(PATH + "/" + newCandidate.getId())).body(newCandidate);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Candidate> update(
      @PathVariable("id") Integer id, @RequestBody Candidate updateDetail)
      throws EntityNotFoundException {
    Candidate foundCandidate = null;
    try {
      foundCandidate = candidateService.update(updateDetail, id);
    } catch (EntityNotFoundException e) {
      log.error("Can not find the candidate with given id.", e);
      throw EntityNotFoundException.notFound(String.valueOf(e.getCause()), e.getMessage());
    }
    return ResponseEntity.created(URI.create(PATH + "/" + id)).body(foundCandidate);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Integer id)
      throws EntityNotFoundException {
    candidateService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
