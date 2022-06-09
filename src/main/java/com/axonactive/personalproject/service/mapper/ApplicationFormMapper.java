package com.axonactive.personalproject.service.mapper;

import com.axonactive.personalproject.entity.ApplicationForm;
import com.axonactive.personalproject.service.dto.ApplicationFormDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ApplicationFormMapper {
    ApplicationFormMapper INSTANCE = Mappers.getMapper(ApplicationFormMapper.class);
    @Mapping(source ="candidate.name" ,target ="candidateName" )
    @Mapping(source = "hiringRequest.position",target ="hiringRequestPosition" )
    @Mapping(source ="recruitmentChanel.name" ,target ="recruitmentChanelName" )
    @Mapping(source = "hrOfficer.name",target ="hrOfficerName" )
    ApplicationFormDto toDto(ApplicationForm applicationForm);
    List<ApplicationFormDto> toDtos (List<ApplicationForm> applicationFormList);
}
