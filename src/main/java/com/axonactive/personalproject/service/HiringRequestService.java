package com.axonactive.personalproject.service;

import com.axonactive.personalproject.controller.request.HiringRequestRequest;
import com.axonactive.personalproject.entity.HiringRequest;
import com.axonactive.personalproject.service.dto.HiringRequestDto;

import java.time.LocalDate;
import java.util.List;

public interface HiringRequestService {
  List<HiringRequestDto> findAll();

  HiringRequestDto findById(Integer id);

  List<HiringRequestDto> findByHiringManagerId(Integer Id);

  HiringRequest add(HiringRequestRequest request);

  HiringRequestDto update(HiringRequestRequest request, Integer id);

  void deleteById(Integer id);

  HiringRequest convertRequestToEntity(HiringRequestRequest request);

  boolean isValidOnboardDate(LocalDate date);

  LocalDate checkValidOnboardDate(LocalDate onboardDate);

  HiringRequest checkValidHiringRequestId(Integer id);

  Boolean isValidHiringRequest(HiringRequest request);

  HiringRequest checkValidHiringRequest(HiringRequest request);
}
