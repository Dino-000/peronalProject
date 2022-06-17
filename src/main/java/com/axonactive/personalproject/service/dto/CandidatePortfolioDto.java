package com.axonactive.personalproject.service.dto;

import com.axonactive.personalproject.entity.Candidate;
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
  private List<CandidateCertificationDto> certificationList;
  private List<CandidateEducationDto> educationList;
  private List<CandidateSkillSetDto> skillSetList;
  private List<WorkingHistoryRecordSkillSetDto> workingHistoryRecordSkillSetDtoList;



}
