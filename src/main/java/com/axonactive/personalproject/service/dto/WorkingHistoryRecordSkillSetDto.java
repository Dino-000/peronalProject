package com.axonactive.personalproject.service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkingHistoryRecordSkillSetDto {
    private String workingHistoryRecordCompany;
    private String candidateName;
    private String skillSetName;
}
