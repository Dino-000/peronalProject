package com.axonactive.personalproject.service.mapper;

import com.axonactive.personalproject.entity.HiringRequestSkillSet;
import com.axonactive.personalproject.service.dto.HiringRequestSkillSetDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface HiringRequestSkillSetMapper {


    HiringRequestSkillSetMapper INSTANCE = Mappers.getMapper(HiringRequestSkillSetMapper.class);

    @Mapping(source ="hiringRequest.position",target ="hiringRequestPosition")
    @Mapping(source ="skillSet.name",target ="skillSetName" )
    HiringRequestSkillSetDto toDto (HiringRequestSkillSet hiringRequestSkillSet);
    List<HiringRequestSkillSetDto> toDtos(List<HiringRequestSkillSet> hiringRequestSkillSets);
}
