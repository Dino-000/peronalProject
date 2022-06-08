package com.axonactive.personalproject.repository;

import com.axonactive.personalproject.entity.HiringRequest;
import com.axonactive.personalproject.entity.HiringRequestSkillSet;
import com.axonactive.personalproject.service.HiringRequestSkillSetService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HiringRequestSkillSetRepository extends JpaRepository<HiringRequestSkillSet,Integer> {
}
