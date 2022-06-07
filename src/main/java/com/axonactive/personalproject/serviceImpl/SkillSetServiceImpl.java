package com.axonactive.personalproject.serviceImpl;

import com.axonactive.personalproject.repository.SkillSetRepository;
import com.axonactive.personalproject.service.SkillSetService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SkillSetServiceImpl implements SkillSetService {
    @Autowired
    SkillSetRepository skillSetRepository;
}
