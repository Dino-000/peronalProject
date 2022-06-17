package com.axonactive.personalproject.service;

import com.axonactive.personalproject.entity.RecruitmentChanel;
import com.axonactive.personalproject.exception.ResourceNotFoundException;

import java.util.List;

public interface RecruitmentChanelService {
    List<RecruitmentChanel> findAll();
    RecruitmentChanel findById(Integer id) throws ResourceNotFoundException;


    RecruitmentChanel add (RecruitmentChanel inputData);
    RecruitmentChanel update (RecruitmentChanel request, Integer id) throws ResourceNotFoundException;

    void deleteById(Integer id) throws ResourceNotFoundException;

    RecruitmentChanel saveRecruitmentChanel(RecruitmentChanel recruitmentChanel);
}
