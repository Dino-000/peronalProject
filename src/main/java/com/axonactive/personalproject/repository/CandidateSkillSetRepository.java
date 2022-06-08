package com.axonactive.personalproject.repository;

import com.axonactive.personalproject.entity.CandidateSkillSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CandidateSkillSetRepository extends JpaRepository<CandidateSkillSet,Integer> {

}
