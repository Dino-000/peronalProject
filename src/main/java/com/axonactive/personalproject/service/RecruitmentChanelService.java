package com.axonactive.personalproject.service;

import com.axonactive.personalproject.entity.RecruitmentChanel;

import java.util.List;
import java.util.Optional;

public interface RecruitmentChanelService {
    List<RecruitmentChanel> findAll();
    Optional<RecruitmentChanel> findById(Integer id);

    void deleteById(Integer id);

    RecruitmentChanel saveRecruitmentChanel(RecruitmentChanel recruitmentChanel);
}
