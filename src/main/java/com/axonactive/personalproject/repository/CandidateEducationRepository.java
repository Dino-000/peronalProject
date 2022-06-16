package com.axonactive.personalproject.repository;

import com.axonactive.personalproject.entity.CandidateEducation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateEducationRepository extends JpaRepository<CandidateEducation,Integer> {
    List<CandidateEducation> findByEducationSchoolName (String schoolName);
    List<CandidateEducation> findByCandidateId (Integer Id);
}
