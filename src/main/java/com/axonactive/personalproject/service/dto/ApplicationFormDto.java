package com.axonactive.personalproject.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationFormDto {

  private LocalDate submittedDate;
  private Integer noticePeriods;
  private String urlCV;
  private double salaryExpectation;
  private String candidateName;
  private String position;
  private String level;
  private String specificBenefit;
  private Double budget;
  private String department;
  private String hiringManager;
  private String recruiter;
  private String recruitmentChanelName;
}
