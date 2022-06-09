package com.axonactive.personalproject.service.mapper;

import com.axonactive.personalproject.entity.CandidateSkillSet;
import com.axonactive.personalproject.service.dto.CandidateSkillSetDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CandidateSkillSetMapper {
    CandidateSkillSetMapper INSTANCE = Mappers.getMapper(CandidateSkillSetMapper.class);
    @Mapping(source = "candidate.name",target = "employeeName")
    @Mapping(source = "skillSet.name",target = "skillSetName")
    CandidateSkillSetDto toDto (CandidateSkillSet candidateEducation);
    List<CandidateSkillSetDto> toDtos(List<CandidateSkillSet> candidateEducations);
}
