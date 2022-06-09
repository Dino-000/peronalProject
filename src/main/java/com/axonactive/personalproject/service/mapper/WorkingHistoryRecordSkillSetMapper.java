package com.axonactive.personalproject.service.mapper;


import com.axonactive.personalproject.entity.WorkingHistoryRecordSkillSet;
import com.axonactive.personalproject.service.dto.WorkingHistoryRecordSkillSetDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface WorkingHistoryRecordSkillSetMapper {

    WorkingHistoryRecordSkillSetMapper INSTANCE = Mappers.getMapper(WorkingHistoryRecordSkillSetMapper.class);

    @Mapping(source = "workingHistoryRecord.companyName",target = "workingHistoryRecordCompany")
    @Mapping(source = "workingHistoryRecord.candidate.name",target = "candidateName")
    @Mapping(source = "skillSet.name",target = "skillSetName")
    WorkingHistoryRecordSkillSetDto toDto (WorkingHistoryRecordSkillSet workingHistoryRecordSkillSet);
    List<WorkingHistoryRecordSkillSetDto> toDtos(List<WorkingHistoryRecordSkillSet> workingHistoryRecordSkillSets);
}
