package com.axonactive.personalproject.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HrPerformanceRecordDto {
    private String quarter;
    private Double quarterKpi;
    private Double quarterPerformance;
    private Double bonus;
    private Double Penalty;
    private boolean peakRate;
    private String hrOfficerName;
}
