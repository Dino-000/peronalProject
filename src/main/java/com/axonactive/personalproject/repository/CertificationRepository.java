package com.axonactive.personalproject.repository;

import com.axonactive.personalproject.entity.Certification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CertificationRepository extends JpaRepository<Certification, Integer> {
  @Query(
      value =
          "SELECT c.certification "
              + "FROM CandidateCertification c "
              + "WHERE c.candidate.id = ?1")
  List<Certification> findByCandidateId(Integer id);
}
