package com.axonactive.personalproject.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkingHistoryRecordDto {
    private String companyName;
    private LocalDate joinedDate;
    private LocalDate resignationDate;
    private String position;
    private String projectName;
    private String responsibility;
    private String client;
    private Integer teamSize;
    private String referencesPeoplePhone;
    private String candidateName;
}
