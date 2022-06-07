package com.axonactive.personalproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public abstract class Employee {

    private String name;
    private LocalDate dateOfBirth;
    private Seniority seniority;


    @ManyToOne
    @JoinColumn(name = "department_ID")
    private Department department;


}
