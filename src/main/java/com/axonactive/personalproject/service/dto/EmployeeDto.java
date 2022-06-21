package com.axonactive.personalproject.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
  private String EmployeeId;
  private String name;
  private LocalDate dateOfBirth;
  private String team;
  private String departmentName;
}
