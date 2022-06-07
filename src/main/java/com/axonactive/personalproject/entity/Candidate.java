package com.axonactive.personalproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;

import javax.persistence.*;
import java.security.cert.Certificate;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Candidate {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;
  private LocalDate dateOfBirth;
  private String location;
  private String occupation;
  private String seniority;
  @OneToMany
  private Set<Education> education;
  @OneToMany
  private Set<Certification> certification;
  @OneToMany
  private Set<SkillSet> skillSet;
  private double gpa;
}
