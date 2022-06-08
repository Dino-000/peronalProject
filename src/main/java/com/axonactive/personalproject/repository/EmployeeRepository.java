package com.axonactive.personalproject.repository;

import com.axonactive.personalproject.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
}
