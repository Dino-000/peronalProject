package com.axonactive.personalproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CandidateEducation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne private Candidate candidate;
  @ManyToOne private Education education;
  @Min(value = 1900, message = "the graduation year can be lower than 1900")
  private Integer graduationYear;
}
