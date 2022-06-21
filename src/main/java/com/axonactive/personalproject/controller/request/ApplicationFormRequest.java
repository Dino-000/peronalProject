package com.axonactive.personalproject.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationFormRequest {
  private LocalDate submittedDate;
  private Integer noticePeriods;
  private String urlCV;
  private double salaryExpectation;
  private Integer candidateId;
  private Integer hiringRequestId;
  private Integer recruitmentChanelId;
  private Integer hrOfficerId;
}
