package com.axonactive.personalproject.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HiringRequestSkillSetDto {
    private String position;
    private String level;
    private String departmentName;
    private String skillSetIndustryCategory;
    private String skillSetName;
    private String skillSetType;
    private String skillSetLevel;
}
