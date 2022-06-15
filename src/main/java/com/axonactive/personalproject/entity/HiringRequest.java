package com.axonactive.personalproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
    @ManyToOne
    private Department department;
    @ManyToOne
    private Employee hiringManager;

}
