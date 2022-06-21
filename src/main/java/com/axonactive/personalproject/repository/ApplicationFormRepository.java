package com.axonactive.personalproject.repository;

import com.axonactive.personalproject.entity.ApplicationForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ApplicationFormRepository extends JpaRepository<ApplicationForm, Integer> {
  List<ApplicationForm> findBySubmittedDateBetween(LocalDate fromDate, LocalDate untilDate);

  List<ApplicationForm> findByHiringRequestHiringManagerId(Integer Id);

  List<ApplicationForm> findFirstByCandidateIdOrderBySubmittedDateDesc(Integer Id);
}
