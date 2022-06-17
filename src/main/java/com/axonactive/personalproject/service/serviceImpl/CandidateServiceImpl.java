package com.axonactive.personalproject.service.serviceImpl;

import com.axonactive.personalproject.entity.Candidate;
import com.axonactive.personalproject.exception.ResourceNotFoundException;
import com.axonactive.personalproject.repository.*;
import com.axonactive.personalproject.service.CandidateService;
import com.axonactive.personalproject.service.dto.CandidatePortfolioDto;
import com.axonactive.personalproject.service.mapper.CandidateCertificationMapper;
import com.axonactive.personalproject.service.mapper.CandidateEducationMapper;
import com.axonactive.personalproject.service.mapper.CandidateSkillSetMapper;
import com.axonactive.personalproject.service.mapper.WorkingHistoryRecordSkillSetMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {
  @Autowired CandidateRepository candidateRepository;
  @Autowired CandidateCertificationRepository candidateCertificationRepository;
  @Autowired CandidateEducationRepository candidateEducationRepository;
  @Autowired CandidateSkillSetRepository candidateSkillSetRepository;
  @Autowired WorkingHistoryRecordSkillSetRepository workingHistoryRecordSkillSetRepository;

  @Override
  public List<Candidate> findAll() {
    return candidateRepository.findAll();
  }

  @Override
  public Candidate findById(Integer id) throws ResourceNotFoundException {
    return candidateRepository
        .findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Can't not find Candidate with that id."));
  }

  @Override
  public void delete(Integer id) throws ResourceNotFoundException {
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

    return null;
  }

  @Override
  public Set<Candidate> findByCertification(String nameOfCertification) {
    return candidateRepository.findByCertification(nameOfCertification);
  }

  @Override
  public CandidatePortfolioDto findPortfolio(Integer id) throws ResourceNotFoundException {
    return new CandidatePortfolioDto(
        candidateRepository
            .findById(id)
            .orElseThrow(
                () -> new ResourceNotFoundException("Can't not find Candidate with that id.")),
        CandidateCertificationMapper.INSTANCE.toDtos(
            candidateCertificationRepository.findByCandidateId(id)),
        CandidateEducationMapper.INSTANCE.toDtos(
            candidateEducationRepository.findByCandidateId(id)),
        CandidateSkillSetMapper.INSTANCE.toDtos(candidateSkillSetRepository.findByCandidateId(id)),
        WorkingHistoryRecordSkillSetMapper.INSTANCE.toDtos(
            workingHistoryRecordSkillSetRepository.findByWorkingHistoryRecordCandidateId(id)));
  }

  @Override
  public Candidate update(Candidate updateDetail, Integer id) throws ResourceNotFoundException {
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
