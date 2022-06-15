package com.axonactive.personalproject.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateEducationDto {
    private String employeeName;
    private String schoolName;
    private String degree;
    private String major;
    private Double prestigeRate;
    private Integer graduationYear;
}
