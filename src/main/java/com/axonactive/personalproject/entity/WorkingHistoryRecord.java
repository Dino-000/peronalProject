package com.axonactive.personalproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
  private Integer teamSize;
  private String jobType;
  private String referencesPeoplePhone;
  @ManyToOne private Candidate candidate;
}
