package com.axonactive.personalproject.service;

import com.axonactive.personalproject.entity.Candidate;

import java.util.List;
import java.util.Set;

public interface CandidateService {
  List<Candidate> findAll();

  Candidate findById(Integer id);

  Set<Candidate> findBySalaryExpectationLessThanAndCandidateSkillSet(
      Double salaryExpectation, String SkillSetName);

  Set<Candidate> findByExperiencedTeamSizeLargerThan(Integer minSize);

  Set<Candidate> findByGPAAndSkillSetAndEducationAndSeniority(
      Double gpa, String skillSetName, String schoolName, String Seniority);

  Set<Candidate> findByExperiencedWithJobType(String jobType);

  Set<Candidate> findByLocationAndSkillSetAndSeniority(
      String location, String skillSetName, String Seniority);

  Set<Candidate> findBySalaryExpectationGreaterThanHiringRequestBudget(Integer id);

  Set<Candidate> findByExperiencesInSpecificCompany(String companyName);

  Set<Candidate> findByEducation(String schoolName);

  Set<Candidate> findByCertification(String nameOfCertification);

  Candidate add(Candidate candidate);

  Candidate update(Candidate candidate, Integer id);

  void delete(Integer id);
}
