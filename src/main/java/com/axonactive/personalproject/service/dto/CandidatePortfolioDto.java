package com.axonactive.personalproject.service.dto;

import com.axonactive.personalproject.entity.Candidate;
import com.axonactive.personalproject.entity.Certification;
import com.axonactive.personalproject.entity.Education;
import com.axonactive.personalproject.entity.SkillSet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CandidatePortfolioDto {
    private Candidate candidate;
    private List<Certification> certificationList;
    private List<Education> educationList;
    private  List<SkillSet> skillSetList;
    private List<WorkingHistoryRecordDto> workingHistoryRecordDtoList;
}
