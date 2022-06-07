package com.axonactive.personalproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
public class HrOfficer extends Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String team;
    public HrOfficer(String name, LocalDate dateOfBirth, Seniority seniority, Department department, Integer id, String team) {
        super(name, dateOfBirth, seniority, department);
        this.id = id;
        this.team = team;
    }
    public HrOfficer(Integer id, String team) {
        this.id = id;
        this.team = team;
    }
}
