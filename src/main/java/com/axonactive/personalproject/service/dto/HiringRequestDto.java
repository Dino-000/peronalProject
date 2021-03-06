package com.axonactive.personalproject.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HiringRequestDto {
  private LocalDate onBoardingDate;
  private String position;
  private String level;
  private Double budget;

  private String specificBenefit;
  private String departmentName;
  private String hiringManagerName;
}
