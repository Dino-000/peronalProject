package com.axonactive.personalproject.repository;

import com.axonactive.personalproject.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository

public interface DepartmentRepository extends JpaRepository<Department,Integer> {

}