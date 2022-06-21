package com.axonactive.personalproject.service.mapper;

import com.axonactive.personalproject.entity.CandidateEducation;
import com.axonactive.personalproject.service.dto.CandidateEducationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CandidateEducationMapper {
  CandidateEducationMapper INSTANCE = Mappers.getMapper(CandidateEducationMapper.class);

  @Mapping(source = "candidate.name", target = "employeeName")
  @Mapping(source = "education.schoolName", target = "schoolName")
  @Mapping(source = "education.degree", target = "degree")
  @Mapping(source = "education.major", target = "major")
  @Mapping(source = "education.prestigeRate", target = "prestigeRate")
  CandidateEducationDto toDto(CandidateEducation candidateEducation);

  List<CandidateEducationDto> toDtos(List<CandidateEducation> candidateEducation);
}
