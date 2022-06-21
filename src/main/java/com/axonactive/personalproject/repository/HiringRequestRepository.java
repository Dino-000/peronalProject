package com.axonactive.personalproject.repository;

import com.axonactive.personalproject.entity.HiringRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HiringRequestRepository extends JpaRepository<HiringRequest, Integer> {
  List<HiringRequest> findByHiringManagerId(Integer Id);
}
