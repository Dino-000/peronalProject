package com.axonactive.personalproject.repository;

import com.axonactive.personalproject.entity.ApplicationForm;
import com.axonactive.personalproject.entity.Candidate;
import com.axonactive.personalproject.entity.WorkingHistoryRecord;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface WorkingHistoryRecordRepository extends JpaRepository<WorkingHistoryRecord,Integer> {


}
