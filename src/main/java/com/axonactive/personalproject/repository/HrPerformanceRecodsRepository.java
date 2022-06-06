package com.axonactive.personalproject.repository;

import com.axonactive.personalproject.entity.HrPerformanceRecods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Repository
public interface HrPerformanceRecodsRepository extends JpaRepository<HrPerformanceRecods,Integer> {

}
