package com.axonactive.personalproject.repository;

import com.axonactive.personalproject.entity.HrPerformanceRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HrPerformanceRecordsRepository extends JpaRepository<HrPerformanceRecords,Integer> {

}
