package com.axonactive.personalproject.repository;

import com.axonactive.personalproject.entity.ApplicationForm;
import com.axonactive.personalproject.entity.HiringRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.time.LocalDate;

@Repository

public interface HiringRequestRepository extends JpaRepository<HiringRequest,Integer> {

}
