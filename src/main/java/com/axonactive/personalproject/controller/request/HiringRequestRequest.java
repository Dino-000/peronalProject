package com.axonactive.personalproject.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HiringRequestRequest {
  private LocalDate onBoardingDate;
  private String position;
  private String level;
  private String specificBenefit;
  private Double budget;

  private Integer departmentId;
  private Integer hiringManagerId;
}
