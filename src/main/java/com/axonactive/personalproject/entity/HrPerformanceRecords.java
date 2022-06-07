package com.axonactive.personalproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class HrPerformanceRecords {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String quarter;
    private Double quarterKpi;
    private Double quarterPerformance;
    private Double bonus;
    private Double Penalty;
    private boolean peakRate;
    @ManyToOne
    private HrOfficer hrOfficer;
}
