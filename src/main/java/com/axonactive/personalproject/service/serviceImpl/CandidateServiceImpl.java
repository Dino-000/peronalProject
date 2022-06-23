package com.axonactive.personalproject.service.serviceImpl;

import com.axonactive.personalproject.entity.Candidate;
import com.axonactive.personalproject.exception.EntityNotFoundException;
import com.axonactive.personalproject.repository.CandidateRepository;
import com.axonactive.personalproject.service.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {
  @Autowired CandidateRepository candidateRepository;

  @Override
  public List<Candidate> findAll() {
    return candidateRepository.findAll();
  }

  @Override
  public Candidate findById(Integer id) {
    return candidateRepository.findById(id).orElseThrow(EntityNotFoundException::candidateNotFound);
  }

  @Override
  public void delete(Integer id) {
    findById(id);
    candidateRepository.deleteById(id);
  }

  @Override
  public Candidate add(Candidate candidate) {
    return candidateRepository.save(candidate);
  }

  @Override
  public Set<Candidate> findBySalaryExpectationLessThanAndCandidateSkillSet(
      Double salaryExpectation, String skillSetName) {
    return candidateRepository.findBySalaryExpectationLessThanAndCandidateSkillSet(
        salaryExpectation, skillSetName);
  }

  @Override
  public Set<Candidate> findByExperiencedTeamSizeLargerThan(Integer minSize) {
    return candidateRepository.findByExperiencedTeamSizeLargerThan(minSize);
  }

  @Override
  public Set<Candidate> findByGPAAndSkillSetAndEducationAndSeniority(
      Double gpa, String skillSetName, String schoolName, String Seniority) {
    return candidateRepository.findByGPAAndSkillSetAndEducationAndSeniority(
        gpa, skillSetName, schoolName, Seniority);
  }

  @Override
  public Set<Candidate> findByExperiencedWithJobType(String jobType) {
    return candidateRepository.findByExperiencedWithJobType(jobType);
  }

  @Override
  public Set<Candidate> findByLocationAndSkillSetAndSeniority(
      String location, String skillSetName, String Seniority) {
    return candidateRepository.findByLocationAndSkillSetAndSeniority(
        location, skillSetName, Seniority);
  }

  @Override
  public Set<Candidate> findBySalaryExpectationGreaterThanHiringRequestBudget(Integer id) {
    return candidateRepository.findBySalaryExpectationGreaterThanHiringRequestBudget(id);
  }

  @Override
  public Set<Candidate> findByExperiencesInSpecificCompany(String companyName) {
    return candidateRepository.findByExperiencesInSpecificCompany(companyName);
  }

  @Override
  public Set<Candidate> findByEducation(String schoolName) {
    return candidateRepository.findByEducation(schoolName);
  }

  @Override
  public Set<Candidate> findByCertification(String nameOfCertification) {
    return candidateRepository.findByCertification(nameOfCertification);
  }

  @Override
  public Candidate update(Candidate updateDetail, Integer id) {
    Candidate updatingCandidate = findById(id);
    updatingCandidate.setName(updateDetail.getName());
    updatingCandidate.setDateOfBirth(updateDetail.getDateOfBirth());
    updatingCandidate.setLocation(updateDetail.getLocation());
    updatingCandidate.setOccupation(updateDetail.getOccupation());
    updatingCandidate.setSeniority(updateDetail.getSeniority());
    updatingCandidate.setGpa(updateDetail.getGpa());
    return add(updatingCandidate);
  }
}
