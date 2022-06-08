package com.axonactive.personalproject.repository;

import com.axonactive.personalproject.entity.HrPerformanceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HrPerformanceRecordRepository extends JpaRepository<HrPerformanceRecord,Integer> {

}
