package com.axonactive.personalproject.repository;

import com.axonactive.personalproject.entity.SkillSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface SkillSetRepository extends JpaRepository<SkillSet,Integer> {
@Query(value = "SELECT c.skillSet " +
        "FROM CandidateSkillSet c " +
        "WHERE c.candidate.id = ?1")
    List<SkillSet> findByCandidateId(Integer id);
}
