package com.axonactive.personalproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ApplicationForm {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private LocalDate submittedDate;
  @Min(value = 0,message = "notice Periods should not less than 0")
  private Integer noticePeriods;
  private String urlCV;
  private double salaryExpectation;
  @ManyToOne
  @NotNull
  private Candidate candidate;
  @ManyToOne
  @NotNull
  private HiringRequest hiringRequest;
  @ManyToOne
  @NotNull
  private RecruitmentChanel recruitmentChanel;
  @ManyToOne
  @NotNull
  private Employee hrOfficer;
}
