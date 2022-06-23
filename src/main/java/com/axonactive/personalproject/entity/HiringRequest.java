package com.axonactive.personalproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.Min;
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
  @Min(value = 0,message = "the budget can not be less than 0")
  private Double budget;
  @ManyToOne private Department department;
  @ManyToOne private Employee hiringManager;
}
