package com.axonactive.personalproject.service;

import com.axonactive.personalproject.controller.request.HiringRequestRequest;
import com.axonactive.personalproject.entity.HiringRequest;
import com.axonactive.personalproject.exception.ResourceNotFoundException;
import com.axonactive.personalproject.service.dto.HiringRequestDto;

import java.util.List;

public interface HiringRequestService {
    List<HiringRequestDto> findAll();
    HiringRequestDto findById(Integer id) throws ResourceNotFoundException;

    void deleteById(Integer id) throws ResourceNotFoundException;

    HiringRequest add(HiringRequestRequest request) throws ResourceNotFoundException;
    HiringRequest convertRequestToDto (HiringRequestRequest request) throws ResourceNotFoundException;
    List<HiringRequestDto> findByHiringManagerId(Integer Id);
    HiringRequestDto update (HiringRequestRequest request, Integer id) throws ResourceNotFoundException;
}
