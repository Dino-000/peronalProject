package com.axonactive.personalproject.repository;

import com.axonactive.personalproject.entity.EmployeeCertification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeCertificationRepository extends JpaRepository<EmployeeCertification,Integer> {

}
