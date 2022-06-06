package com.axonactive.personalproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;

import javax.persistence.*;
import java.security.cert.Certificate;
import java.time.LocalDate;
import java.util.List;

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
    @ManyToOne
    private List<Education> education;
    @ManyToOne
    private List<Certification> certification;
    @ManyToOne
    private List<SkillSet> skillSet;
    private double GPA;
}
