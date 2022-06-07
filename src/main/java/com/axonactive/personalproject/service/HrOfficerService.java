package com.axonactive.personalproject.service;

import com.axonactive.personalproject.entity.HrOfficer;

import java.util.List;
import java.util.Optional;

public interface HrOfficerService {
    List<HrOfficer> findAll();
    Optional<HrOfficer> findById(Integer id);

    void deleteById(Integer id);

    HrOfficer saveHrOfficer(HrOfficer hrOfficer);
}
