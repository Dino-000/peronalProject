package com.axonactive.personalproject.service.mapper;

import com.axonactive.personalproject.entity.HiringRequest;
import com.axonactive.personalproject.service.dto.HiringRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface HiringRequestMapper {

  HiringRequestMapper INSTANCE = Mappers.getMapper(HiringRequestMapper.class);

  @Mapping(source = "department.name", target = "departmentName")
  @Mapping(source = "hiringManager.name", target = "hiringManagerName")
  HiringRequestDto toDto(HiringRequest hiringRequest);

  List<HiringRequestDto> toDtos(List<HiringRequest> hiringRequests);
}
