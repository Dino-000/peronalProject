package com.axonactive.personalproject.service;

import com.axonactive.personalproject.controller.request.HiringRequestRequest;
import com.axonactive.personalproject.entity.Department;
import com.axonactive.personalproject.entity.Employee;
import com.axonactive.personalproject.entity.HiringRequest;
import com.axonactive.personalproject.exception.ResourceNotFoundException;
import com.axonactive.personalproject.service.dto.HiringRequestDto;

import java.time.LocalDate;
import java.util.List;

public interface HiringRequestService {
    List<HiringRequestDto> findAll();
    HiringRequestDto findById(Integer id) throws ResourceNotFoundException;

    List<HiringRequestDto> findByHiringManagerId(Integer Id);

    HiringRequest add(HiringRequestRequest request) throws ResourceNotFoundException;
    HiringRequestDto update (HiringRequestRequest request, Integer id) throws ResourceNotFoundException;
    void deleteById(Integer id) throws ResourceNotFoundException;
    HiringRequest convertRequestToEntity (HiringRequestRequest request) throws ResourceNotFoundException;

    boolean isValidHiringManager(HiringRequest request) ;
    boolean isHiringManager(HiringRequest request) ;
    boolean isValidOnboardDate(LocalDate date);
}
