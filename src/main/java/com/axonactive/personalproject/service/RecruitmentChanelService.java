package com.axonactive.personalproject.service;

import com.axonactive.personalproject.entity.RecruitmentChanel;

import java.util.List;

public interface RecruitmentChanelService {
  List<RecruitmentChanel> findAll();

  RecruitmentChanel findById(Integer id);

  RecruitmentChanel add(RecruitmentChanel inputData);

  RecruitmentChanel update(RecruitmentChanel request, Integer id);

  void deleteById(Integer id);

  RecruitmentChanel saveRecruitmentChanel(RecruitmentChanel recruitmentChanel);
}
