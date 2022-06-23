package com.axonactive.personalproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Constraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class WorkingHistoryRecord {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String companyName;
  private LocalDate joinedDate;
  private LocalDate resignationDate;
  private String position;
  private String projectName;
  private String responsibility;
  private String client;
  @Min(value = 0, message = "The team size can not be less than 0")
  private Integer teamSize;
  private String jobType;

  @Pattern(regexp = "\\d{10}",message = "the phone number should be 10 number from 0-9")
  private String referencesPeoplePhone;
  @ManyToOne private Candidate candidate;
}
