package com.axonactive.personalproject.repository;

import com.axonactive.personalproject.entity.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationRepository extends JpaRepository<Education, Integer> {
  @Query(value = "SELECT c.education " + "FROM CandidateEducation c " + "WHERE c.candidate.id = ?1")
  List<Education> findByCandidateId(Integer id);
}
