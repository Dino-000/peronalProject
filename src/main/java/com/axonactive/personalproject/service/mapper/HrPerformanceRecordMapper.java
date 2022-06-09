package com.axonactive.personalproject.service.mapper;

import com.axonactive.personalproject.entity.HrPerformanceRecord;
import com.axonactive.personalproject.service.dto.HrPerformanceRecordDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface HrPerformanceRecordMapper {

    HrPerformanceRecordMapper INSTANCE = Mappers.getMapper(HrPerformanceRecordMapper.class);

    @Mapping(source ="hrOfficer.name" ,target = "hrOfficerName")
    HrPerformanceRecordDto toDto (HrPerformanceRecord hrPerformanceRecord);
    List<HrPerformanceRecordDto> toDtos(List<HrPerformanceRecord> hrPerformanceRecords);
}
