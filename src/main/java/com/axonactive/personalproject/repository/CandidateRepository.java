package com.axonactive.personalproject.repository;

import com.axonactive.personalproject.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Integer> {

    @Query(value = "SELECT c.candidate " +
            "FROM CandidateSkillSet c, ApplicationForm a " +
            "WHERE c.candidate.id= a.candidate.id " +
            "AND a.salaryExpectation<= ?1 " +
            "AND c.skillSet.name = ?2")
    List<Candidate> findBySalaryExpectationLessThanAndCandidateSkillSet (Double salaryExpectation, String SkillSetName);

    @Query(value = "SELECT w.candidate " +
            "FROM WorkingHistoryRecord w " +
            "WHERE w.teamSize <= ?1")
    List<Candidate> findByExperiencedTeamSizeLargerThan (Integer minSize);

    @Query(value = "SELECT c " +
            "FROM Candidate c, CandidateSkillSet c1, CandidateEducation c2 " +
            "WHERE c.id = c1.candidate.id AND c.id = c2.candidate.id " +
            "AND c.gpa= ?1 AND c1.skillSet.name = ?2 AND c2.education.schoolName =?3 AND c.seniority =?4")
    List<Candidate> findByGPAAndSkillSetAndEducationAndSeniority(Double gpa, String skillSetName, String schoolName,String Seniority);

    @Query (value = "SELECT w.candidate " +
            "FROM WorkingHistoryRecord w " +
            "WHERE w.jobType =?1")
    List<Candidate> findByExperiencedWithJobType (String jobType);

    @Query(value = "SELECT s.candidate " +
            "FROM CandidateSkillSet s " +
            "WHERE s.candidate.location =?1 AND s.skillSet.name =?2 AND s.candidate.seniority =?3")
    List<Candidate> findByLocationAndSkillSetAndSeniority (String location, String skillSetName, String Seniority);

}
