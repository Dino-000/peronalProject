package com.axonactive.personalproject.service.serviceImpl;

import com.axonactive.personalproject.entity.SkillSet;
import com.axonactive.personalproject.repository.SkillSetRepository;
import com.axonactive.personalproject.service.SkillSetService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SkillSetServiceImpl implements SkillSetService {
    @Autowired
    SkillSetRepository skillSetRepository;

    @Override
    public List<SkillSet> findAll() {
        return skillSetRepository.findAll();
    }

    @Override
    public Optional<SkillSet> findById(Integer id) {
        return skillSetRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
skillSetRepository.deleteById(id);
    }

    @Override
    public SkillSet saveSkillSet(SkillSet skillSet) {
        return skillSetRepository.save(skillSet);
    }
}
