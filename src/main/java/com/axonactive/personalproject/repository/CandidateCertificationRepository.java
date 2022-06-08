package com.axonactive.personalproject.repository;

import com.axonactive.personalproject.entity.CandidateCertification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateCertificationRepository extends JpaRepository<CandidateCertification,Integer> {

}
