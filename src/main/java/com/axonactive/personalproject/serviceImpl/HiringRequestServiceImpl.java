package com.axonactive.personalproject.serviceImpl;

import com.axonactive.personalproject.entity.HiringRequest;
import com.axonactive.personalproject.repository.HiringRequestRepository;
import com.axonactive.personalproject.service.HiringRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HiringRequestServiceImpl implements HiringRequestService {
@Autowired
    HiringRequestRepository hiringRequestRepository;

    @Override
    public List<HiringRequest> findAll() {
        return hiringRequestRepository.findAll();
    }

    @Override
    public Optional<HiringRequest> findById(Integer id) {
        return hiringRequestRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
hiringRequestRepository.deleteById(id);
    }

    @Override
    public HiringRequest saveHiringRequest(HiringRequest hiringRequest) {
        return hiringRequestRepository.save(hiringRequest);
    }
}
