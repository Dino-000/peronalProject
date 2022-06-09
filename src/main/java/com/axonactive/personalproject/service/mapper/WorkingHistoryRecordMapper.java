package com.axonactive.personalproject.service.mapper;

import com.axonactive.personalproject.entity.WorkingHistoryRecord;
import com.axonactive.personalproject.service.dto.WorkingHistoryRecordDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface WorkingHistoryRecordMapper {

    WorkingHistoryRecordMapper INSTANCE = Mappers.getMapper(WorkingHistoryRecordMapper.class);

    @Mapping(source = "candidate.name",target = "candidateName")
    WorkingHistoryRecordDto toDto (WorkingHistoryRecord workingHistoryRecord);
     List<WorkingHistoryRecordDto> toDtos(List<WorkingHistoryRecord> workingHistoryRecords);
}
