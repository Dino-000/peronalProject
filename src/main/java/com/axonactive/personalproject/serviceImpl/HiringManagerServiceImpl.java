package com.axonactive.personalproject.serviceImpl;

import com.axonactive.personalproject.entity.HiringManager;
import com.axonactive.personalproject.repository.HiringManagerRepository;
import com.axonactive.personalproject.service.HiringManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HiringManagerServiceImpl implements HiringManagerService {
@Autowired
    HiringManagerRepository hiringManagerRepository;

    @Override
    public List<HiringManager> findAll() {
        return hiringManagerRepository.findAll();
    }

    @Override
    public Optional<HiringManager> findById(Integer id) {
        return hiringManagerRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
hiringManagerRepository.deleteById(id);
    }

    @Override
    public HiringManager saveHiringManager(HiringManager hiringManager) {
        return hiringManagerRepository.save(hiringManager);
    }
}
