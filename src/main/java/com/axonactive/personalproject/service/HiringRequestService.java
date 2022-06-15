package com.axonactive.personalproject.service;

import com.axonactive.personalproject.entity.HiringRequest;

import java.util.List;
import java.util.Optional;

public interface HiringRequestService {
    List<HiringRequest> findAll();
    Optional<HiringRequest> findById(Integer id);

    void deleteById(Integer id);

    HiringRequest saveHiringRequest(HiringRequest hiringRequest);
    List<HiringRequest> findByHiringManagerId(Integer Id);

}
