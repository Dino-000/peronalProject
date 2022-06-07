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
public class HiringRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer headCount;
    private LocalDate onBoardingDate;
    private String position;
    private  String technicalRequirement;
    private String specificBenefit;
    private  Double bonusPoint;
    @ManyToOne
    private ApplicationForm applicationForm;
    @ManyToOne
    private Department department;
    @ManyToOne
    private HiringManager hiringManager;
    @ManyToOne
    private HrOfficer hrOfficer;
}
