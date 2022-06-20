package com.axonactive.personalproject.service;

import com.axonactive.personalproject.controller.request.HiringRequestRequest;
import com.axonactive.personalproject.entity.HiringRequest;
import com.axonactive.personalproject.exception.EntityNotFoundException;
import com.axonactive.personalproject.service.dto.HiringRequestDto;

import java.time.LocalDate;
import java.util.List;

public interface HiringRequestService {
    List<HiringRequestDto> findAll();
    HiringRequestDto findById(Integer id) throws EntityNotFoundException;

    List<HiringRequestDto> findByHiringManagerId(Integer Id);

    HiringRequest add(HiringRequestRequest request) throws EntityNotFoundException;
    HiringRequestDto update (HiringRequestRequest request, Integer id) throws EntityNotFoundException;
    void deleteById(Integer id) throws EntityNotFoundException;
    HiringRequest convertRequestToEntity (HiringRequestRequest request) throws EntityNotFoundException;

    boolean isValidHiringManager(HiringRequest request) ;
    boolean isHiringManager(HiringRequest request) ;
    boolean isValidOnboardDate(LocalDate date);
}
