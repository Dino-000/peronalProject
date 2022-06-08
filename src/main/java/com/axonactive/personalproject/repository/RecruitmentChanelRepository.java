package com.axonactive.personalproject.repository;

import com.axonactive.personalproject.entity.RecruitmentChanel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository


public interface RecruitmentChanelRepository extends JpaRepository<RecruitmentChanel,Integer> {

}
