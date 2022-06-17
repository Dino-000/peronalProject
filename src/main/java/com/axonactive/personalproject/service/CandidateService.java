package com.axonactive.personalproject.service;

import com.axonactive.personalproject.entity.Candidate;
import com.axonactive.personalproject.exception.ResourceNotFoundException;
import com.axonactive.personalproject.service.dto.CandidatePortfolioDto;

import java.util.List;
import java.util.Set;

public interface CandidateService {
    List<Candidate> findAll();
    Candidate findById(Integer id) throws ResourceNotFoundException;



    Set<Candidate> findBySalaryExpectationLessThanAndCandidateSkillSet (Double salaryExpectation, String SkillSetName);

    Set<Candidate> findByExperiencedTeamSizeLargerThan (Integer minSize);
    Set<Candidate> findByGPAAndSkillSetAndEducationAndSeniority(Double gpa, String skillSetName, String schoolName,String Seniority);
    Set<Candidate> findByExperiencedWithJobType (String jobType);
    Set<Candidate> findByLocationAndSkillSetAndSeniority (String location, String skillSetName, String Seniority);
    Set<Candidate> findBySalaryExpectationGreaterThanHiringRequestBudget();

    Set<Candidate> findByExperiencesInSpecificCompany(String companyName);
    Set<Candidate> findByEducation (String schoolName);
    CandidatePortfolioDto findBCandidatePortfolioById (Integer id);
    Set<Candidate> findByCertification (String nameOfCertification);

    CandidatePortfolioDto findPortfolio (Integer id) throws ResourceNotFoundException;
    Candidate add(Candidate candidate);
    Candidate update (Candidate candidate,Integer id) throws ResourceNotFoundException;
    void delete(Integer id) throws ResourceNotFoundException;
}
