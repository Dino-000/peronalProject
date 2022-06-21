package com.axonactive.personalproject.repository;

import com.axonactive.personalproject.entity.WorkingHistoryRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkingHistoryRecordRepository
    extends JpaRepository<WorkingHistoryRecord, Integer> {
  List<WorkingHistoryRecord> findByCompanyName(String companyName);

  List<WorkingHistoryRecord> findByCandidateId(Integer id);
}
