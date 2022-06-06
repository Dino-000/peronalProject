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
public class ApplicationForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate submittedDate;
    private Integer noticePeriods;
    private String urlCV;
    private double salaryExpectation;
    @ManyToOne
    private Candidate candidate;
    @ManyToOne
    private HiringRequest hiringRequest;
    @ManyToOne
    private RecruitmentChanel recruitmentChanel;
    @ManyToOne
    private  HrOfficer hrOfficer;
}