package com.axonactive.personalproject.service;

import com.axonactive.personalproject.entity.HiringManager;

import java.util.List;
import java.util.Optional;

public interface HiringManagerService {
    List<HiringManager> findAll();
    Optional<HiringManager> findById(Integer id);

    void deleteById(Integer id);

    HiringManager saveHiringManager(HiringManager hiringManager);
}
