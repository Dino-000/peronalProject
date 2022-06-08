package com.axonactive.personalproject.repository;

import com.axonactive.personalproject.entity.SkillSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface SkillSetRepository extends JpaRepository<SkillSet,Integer> {

}
