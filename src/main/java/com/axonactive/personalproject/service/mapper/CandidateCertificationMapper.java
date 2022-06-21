package com.axonactive.personalproject.service.mapper;

import com.axonactive.personalproject.entity.CandidateCertification;
import com.axonactive.personalproject.service.dto.CandidateCertificationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CandidateCertificationMapper {
  CandidateCertificationMapper INSTANCE = Mappers.getMapper(CandidateCertificationMapper.class);

  @Mapping(source = "candidate.name", target = "candidateName")
  @Mapping(source = "certification.nameOfCertification", target = "certificationName")
  @Mapping(source = "certification.issuerName", target = "issuerName")
  @Mapping(source = "certification.type", target = "type")
  CandidateCertificationDto toDto(CandidateCertification candidateCertification);

  List<CandidateCertificationDto> toDtos(List<CandidateCertification> candidateCertification);
}
