package com.axonactive.personalproject.repository;

import com.axonactive.personalproject.entity.WorkingHistoryRecordSkillSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkingHistoryRecordSkillSetRepository extends JpaRepository<WorkingHistoryRecordSkillSet,Integer> {
}
