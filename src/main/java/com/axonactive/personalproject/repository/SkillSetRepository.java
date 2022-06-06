package com.axonactive.personalproject.repository;

import com.axonactive.personalproject.entity.ApplicationForm;
import com.axonactive.personalproject.entity.SkillSet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Repository

public interface SkillSetRepository extends JpaRepository<SkillSet,Integer> {

}
