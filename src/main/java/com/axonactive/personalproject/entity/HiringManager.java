package com.axonactive.personalproject.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
public class HiringManager extends Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public HiringManager(String name, LocalDate dateOfBirth, Seniority seniority, Department department, Integer id) {
        super(name, dateOfBirth, seniority, department);
        this.id = id;
    }

    public HiringManager() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        HiringManager that = (HiringManager) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
