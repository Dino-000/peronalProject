package com.axonactive.personalproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RecruitmentChanel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;
  private String adminAccount;
  @Min(value = 0, message = "The annual membership fee can not be less than 0")
  private Double annualMembershipFee;
  @Min(value = 0, message = "The conversion Rate can not be less than 0")
  @Max(value = 1,message = "The conversion Rate can not be greater than 1" )
  private double conversionRate;
  @Min(value = 0, message = "The number Of Successful Placement can not be less than 0")
  private Integer numberOfSuccessfulPlacement;
}
