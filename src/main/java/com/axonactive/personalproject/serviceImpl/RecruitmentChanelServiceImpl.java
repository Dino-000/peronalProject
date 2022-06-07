package com.axonactive.personalproject.serviceImpl;

import com.axonactive.personalproject.repository.RecruitmentChanelRepository;
import com.axonactive.personalproject.service.RecruitmentChanelService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecruitmentChanelServiceImpl implements RecruitmentChanelService {
@Autowired
    RecruitmentChanelRepository recruitmentChanelRepository;
}
