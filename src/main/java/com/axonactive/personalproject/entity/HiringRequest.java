package com.axonactive.personalproject.entity;

import com.axonactive.personalproject.exception.BusinessConstraintException;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDate;

@Slf4j
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class HiringRequest {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private LocalDate onBoardingDate;
  private String position;
  private String level;
  private String specificBenefit;
  private Double budget;
  @ManyToOne private Department department;
  @ManyToOne private Employee hiringManager;
}
