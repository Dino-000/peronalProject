package com.axonactive.personalproject.service;

import com.axonactive.personalproject.entity.RecruitmentChanel;
import com.axonactive.personalproject.exception.EntityNotFoundException;

import java.util.List;

public interface RecruitmentChanelService {
    List<RecruitmentChanel> findAll();
    RecruitmentChanel findById(Integer id) throws EntityNotFoundException;


    RecruitmentChanel add (RecruitmentChanel inputData);
    RecruitmentChanel update (RecruitmentChanel request, Integer id) throws EntityNotFoundException;

    void deleteById(Integer id) throws EntityNotFoundException;

    RecruitmentChanel saveRecruitmentChanel(RecruitmentChanel recruitmentChanel);
}
