package com.axonactive.personalproject.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkingHistoryRecordRequest {
  private String companyName;
  private LocalDate joinedDate;
  private LocalDate resignationDate;
  private String position;
  private String projectName;
  private String responsibility;
  private String client;
  private Integer teamSize;
  private String jobType;
  @Pattern(regexp = "\\d{10}",message = "the phone number should be 10 number from 0-9")
  private String referencesPeoplePhone;
  private Integer candidateId;
}
