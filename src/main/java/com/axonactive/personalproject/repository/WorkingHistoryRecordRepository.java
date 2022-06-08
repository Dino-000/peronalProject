package com.axonactive.personalproject.repository;

import com.axonactive.personalproject.entity.WorkingHistoryRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkingHistoryRecordRepository extends JpaRepository<WorkingHistoryRecord,Integer> {


}
