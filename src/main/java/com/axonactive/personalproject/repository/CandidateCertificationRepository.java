package com.axonactive.personalproject.repository;

import com.axonactive.personalproject.entity.CandidateCertification;
import com.axonactive.personalproject.entity.CandidateEducation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateCertificationRepository extends JpaRepository<CandidateCertification,Integer> {
    List<CandidateCertification> findByCertificationNameOfCertification (String certificationName);
}
