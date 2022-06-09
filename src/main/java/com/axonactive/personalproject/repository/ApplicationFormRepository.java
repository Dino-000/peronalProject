package com.axonactive.personalproject.repository;

import com.axonactive.personalproject.entity.ApplicationForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationFormRepository extends JpaRepository<ApplicationForm,Integer> {

}