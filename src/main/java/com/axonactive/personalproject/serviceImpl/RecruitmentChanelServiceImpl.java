package com.axonactive.personalproject.serviceImpl;

import com.axonactive.personalproject.entity.RecruitmentChanel;
import com.axonactive.personalproject.repository.RecruitmentChanelRepository;
import com.axonactive.personalproject.service.RecruitmentChanelService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecruitmentChanelServiceImpl implements RecruitmentChanelService {
@Autowired
    RecruitmentChanelRepository recruitmentChanelRepository;

    @Override
    public List<RecruitmentChanel> findAll() {
        return recruitmentChanelRepository.findAll();
    }

    @Override
    public Optional<RecruitmentChanel> findById(Integer id) {
        return recruitmentChanelRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
recruitmentChanelRepository.deleteById(id);
    }

    @Override
    public RecruitmentChanel saveRecruitmentChanel(RecruitmentChanel recruitmentChanel) {
        return recruitmentChanelRepository.save(recruitmentChanel);
    }
}
