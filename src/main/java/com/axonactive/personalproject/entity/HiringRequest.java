package com.axonactive.personalproject.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class HiringRequest {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private LocalDate onBoardingDate;
  private String position;
  private String level;
  private String specificBenefit;
  private Double budget;
  @ManyToOne private Department department;
  @ManyToOne private Employee hiringManager;

  public HiringRequest(
      Integer id,
      LocalDate onBoardingDate,
      String position,
      String level,
      String specificBenefit,
      Double budget,
      Department department,
      Employee hiringManager) {
    this.id = id;
    this.onBoardingDate = onBoardingDate;
    this.position = position;
    this.level = level;
    this.specificBenefit = specificBenefit;
    this.budget = budget;
    this.department = department;
    if (isValidHiringManager(department, hiringManager)) {
      this.hiringManager = hiringManager;
    } else {
      throw new IllegalArgumentException(
          "The Hiring manager with id "
              + hiringManager.getEmployeeId()
              + " can not set hiring request for department: "
              + department.getName());
    }
  }

  public void setDepartment(Department department) {
    if (isValidHiringManager(department, this.hiringManager)) {
      this.department = department;
    } else {
      throw new IllegalArgumentException(
          "The Hiring manager with id "
              + hiringManager.getEmployeeId()
              + " can not set hiring request for department: "
              + department.getName());
    }
  }

  public void setHiringManager(Employee hiringManager) {
    this.hiringManager = hiringManager;
  }

  public boolean isValidHiringManager(Department department, Employee hiringManager) {
    return !(hiringManager.getDepartment().getName().equals("HR"))
        || (hiringManager.getDepartment().getName().equals("HR")
            && hiringManager.getDepartment().getManagerID().equals(hiringManager.getEmployeeId())
            && hiringManager.getDepartment().getName().equals(department.getName()));
  }
}
