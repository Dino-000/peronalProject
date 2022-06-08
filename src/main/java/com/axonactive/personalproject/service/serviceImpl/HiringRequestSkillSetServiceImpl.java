package com.axonactive.personalproject.service.serviceImpl;

import com.axonactive.personalproject.entity.HiringRequestSkillSet;
import com.axonactive.personalproject.repository.HiringRequestSkillSetRepository;
import com.axonactive.personalproject.service.HiringRequestSkillSetService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HiringRequestSkillSetServiceImpl implements HiringRequestSkillSetService {
    @Autowired
    HiringRequestSkillSetRepository hiringRequestSkillSetRepository;

    @Override
    public List<HiringRequestSkillSet> findAll() {
        return hiringRequestSkillSetRepository.findAll();
    }

    @Override
    public Optional<HiringRequestSkillSet> findById(Integer id) {
        return hiringRequestSkillSetRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
hiringRequestSkillSetRepository.deleteById(id);
    }

    @Override
    public HiringRequestSkillSet saveHiringRequestSkillSet(HiringRequestSkillSet hiringRequestSkillSet) {
        return hiringRequestSkillSetRepository.save(hiringRequestSkillSet);
    }
}
