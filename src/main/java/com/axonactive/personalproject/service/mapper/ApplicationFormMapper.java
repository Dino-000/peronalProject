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
    @Mapping(source = "hiringRequest.position",target ="position" )
    @Mapping(source = "hiringRequest.level",target ="level" )
    @Mapping(source = "hiringRequest.specificBenefit",target ="specificBenefit" )
    @Mapping(source = "hiringRequest.department.name",target ="department" )
    @Mapping(source ="hiringRequest.budget" ,target ="budget" )
    @Mapping(source = "hiringRequest.hiringManager.name",target ="hiringManager" )
    @Mapping(source = "hrOfficer.name",target ="recruiter" )
    @Mapping(source = "recruitmentChanel.name",target ="recruitmentChanelName" )
    ApplicationFormDto toDto(ApplicationForm applicationForm);
    List<ApplicationFormDto> toDtos (List<ApplicationForm> applicationFormList);
}
