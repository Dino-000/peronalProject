package com.axonactive.personalproject.service.mapper;

import com.axonactive.personalproject.entity.Employee;
import com.axonactive.personalproject.service.dto.EmployeeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EmployeeMapper {
  EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

  @Mapping(source = "department.name", target = "departmentName")
  EmployeeDto toDto(Employee employee);

  List<EmployeeDto> toDtos(List<Employee> employees);
}
