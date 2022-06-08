package com.axonactive.personalproject.repository;

import com.axonactive.personalproject.entity.HiringRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface HiringRequestRepository extends JpaRepository<HiringRequest,Integer> {

}
