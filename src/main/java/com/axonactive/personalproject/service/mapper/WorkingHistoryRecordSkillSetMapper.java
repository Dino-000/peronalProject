package com.axonactive.personalproject.service.mapper;

import com.axonactive.personalproject.entity.WorkingHistoryRecordSkillSet;
import com.axonactive.personalproject.service.dto.WorkingHistoryRecordSkillSetDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface WorkingHistoryRecordSkillSetMapper {

  WorkingHistoryRecordSkillSetMapper INSTANCE =
      Mappers.getMapper(WorkingHistoryRecordSkillSetMapper.class);

  @Mapping(source = "workingHistoryRecord.companyName", target = "companyName")
  @Mapping(source = "workingHistoryRecord.joinedDate", target = "joinedDate")
  @Mapping(source = "workingHistoryRecord.resignationDate", target = "resignationDate")
  @Mapping(source = "workingHistoryRecord.position", target = "position")
  @Mapping(source = "workingHistoryRecord.projectName", target = "projectName")
  @Mapping(source = "workingHistoryRecord.responsibility", target = "responsibility")
  @Mapping(source = "workingHistoryRecord.client", target = "client")
  @Mapping(source = "workingHistoryRecord.teamSize", target = "teamSize")
  @Mapping(source = "workingHistoryRecord.jobType", target = "jobType")
  @Mapping(source = "workingHistoryRecord.referencesPeoplePhone", target = "referencesPeoplePhone")
  @Mapping(source = "skillSet.industryCategory", target = "skillSetIndustryCategory")
  @Mapping(source = "skillSet.name", target = "skillSetName")
  @Mapping(source = "skillSet.type", target = "skillSetType")
  @Mapping(source = "skillSet.level", target = "skillSetLevel")
  @Mapping(source = "workingHistoryRecord.candidate.name", target = "candidateName")
  WorkingHistoryRecordSkillSetDto toDto(WorkingHistoryRecordSkillSet workingHistoryRecordSkillSet);

  List<WorkingHistoryRecordSkillSetDto> toDtos(
      List<WorkingHistoryRecordSkillSet> workingHistoryRecordSkillSets);
}
