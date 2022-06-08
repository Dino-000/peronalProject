package com.axonactive.personalproject.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HrPerformanceRecordRequest {
    private String quarter;
    private Double quarterKpi;
    private Double quarterPerformance;
    private Double bonus;
    private Double Penalty;
    private boolean peakRate;
    private Integer hrOfficerId;
}
