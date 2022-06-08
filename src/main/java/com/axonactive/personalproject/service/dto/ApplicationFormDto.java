package com.axonactive.personalproject.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ApplicationFormDto {
    private LocalDate submittedDate;
    private Integer noticePeriods;
    private String urlCV;
    private double salaryExpectation;
    private String candidateName;
    private String hiringRequestPosition;
    private String recruitmentChanelName;
    private String hrOfficerName;
}
