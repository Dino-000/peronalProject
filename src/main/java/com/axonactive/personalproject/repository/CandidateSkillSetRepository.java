package com.axonactive.personalproject.repository;

import com.axonactive.personalproject.entity.CandidateSkillSet;
import com.axonactive.personalproject.entity.SkillSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateSkillSetRepository extends JpaRepository<CandidateSkillSet,Integer> {
List<CandidateSkillSet> findByCandidateId(Integer id);
}
