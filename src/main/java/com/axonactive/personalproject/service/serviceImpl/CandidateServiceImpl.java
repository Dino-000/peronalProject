package com.axonactive.personalproject.service.serviceImpl;

import com.axonactive.personalproject.entity.Candidate;
import com.axonactive.personalproject.repository.CandidateRepository;
import com.axonactive.personalproject.service.CandidateService;
import com.axonactive.personalproject.service.dto.CandidatePortfolioDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {
@Autowired
    CandidateRepository candidateRepository;
@Autowired


    @Override
    public List<Candidate> findAll() {
        return candidateRepository.findAll();
    }

    @Override
    public Optional<Candidate> findById(Integer id) {
        return candidateRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
    candidateRepository.deleteById(id);
    }

    @Override
    public Candidate saveCandidate(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    @Override
    public Set<Candidate> findBySalaryExpectationLessThanAndCandidateSkillSet(Double salaryExpectation, String skillSetName) {
        return candidateRepository.findBySalaryExpectationLessThanAndCandidateSkillSet(salaryExpectation,skillSetName);
    }

    @Override
    public Set<Candidate> findByExperiencedTeamSizeLargerThan(Integer minSize) {
        return candidateRepository.findByExperiencedTeamSizeLargerThan(minSize);
    }

    @Override
    public Set<Candidate> findByGPAAndSkillSetAndEducationAndSeniority(Double gpa, String skillSetName, String schoolName, String Seniority) {
        return candidateRepository.findByGPAAndSkillSetAndEducationAndSeniority(gpa, skillSetName, schoolName, Seniority);
    }

    @Override
    public Set<Candidate> findByExperiencedWithJobType(String jobType) {
        return candidateRepository.findByExperiencedWithJobType(jobType);
    }

    @Override
    public Set<Candidate> findByLocationAndSkillSetAndSeniority(String location, String skillSetName, String Seniority) {
        return candidateRepository.findByLocationAndSkillSetAndSeniority(location, skillSetName, Seniority);
    }

    @Override
    public Set<Candidate> findBySalaryExpectationGreaterThanHiringRequestBudget() {
        return candidateRepository.findBySalaryExpectationGreaterThanHiringRequestBudget();
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
    public CandidatePortfolioDto findBCandidatePortfolioById(Integer id) {

        return  null;
    }

    @Override
    public Set<Candidate> findByCertification(String nameOfCertification) {
        return candidateRepository.findByCertification(nameOfCertification);
    }


}
