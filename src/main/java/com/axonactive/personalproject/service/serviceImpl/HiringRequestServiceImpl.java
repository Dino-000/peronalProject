package com.axonactive.personalproject.service.serviceImpl;

import com.axonactive.personalproject.controller.request.HiringRequestRequest;
import com.axonactive.personalproject.entity.HiringRequest;
import com.axonactive.personalproject.exception.BusinessConstraintException;
import com.axonactive.personalproject.exception.EntityNotFoundException;
import com.axonactive.personalproject.repository.HiringRequestRepository;
import com.axonactive.personalproject.service.DepartmentService;
import com.axonactive.personalproject.service.EmployeeService;
import com.axonactive.personalproject.service.HiringRequestService;
import com.axonactive.personalproject.service.dto.HiringRequestDto;
import com.axonactive.personalproject.service.mapper.HiringRequestMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class HiringRequestServiceImpl implements HiringRequestService {
  @Autowired HiringRequestRepository hiringRequestRepository;
  @Autowired DepartmentService departmentService;
  @Autowired EmployeeService employeeService;

  @Override
  public List<HiringRequestDto> findAll() {
    return HiringRequestMapper.INSTANCE.toDtos(hiringRequestRepository.findAll());
  }

  @Override
  public HiringRequestDto findById(Integer id) {
    return HiringRequestMapper.INSTANCE.toDto(
        hiringRequestRepository
            .findById(id)
            .orElseThrow(EntityNotFoundException::hiringRequestNotFound));
  }

  @Override
  public void deleteById(Integer id) {
    findById(id);
    hiringRequestRepository.deleteById(id);
  }

  @Override
  public HiringRequest add(HiringRequestRequest request) {
    return hiringRequestRepository.save(convertRequestToEntity(request));
  }

  @Override
  public HiringRequest convertRequestToEntity(HiringRequestRequest request) {
    return new HiringRequest(
        null,
        request.getOnBoardingDate(),
        request.getPosition(),
        request.getLevel(),
        request.getSpecificBenefit(),
        request.getBudget(),
        departmentService.findById(request.getDepartmentId()),
        employeeService.checkValidHiringManagerId(request.getHiringManagerId()));
  }

  @Override
  public Boolean isValidHiringRequest(HiringRequest request) {
    return request
        .getHiringManager()
        .getDepartment()
        .getName()
        .equals(request.getDepartment().getName());
  }

  public HiringRequest checkValidHiringRequest(HiringRequest request) {
    if (isValidHiringRequest(request)) {
      return request;
    } else {
      throw BusinessConstraintException.hiringManagerDepartmentMissMatch();
    }
  }

  @Override
  public boolean isValidOnboardDate(LocalDate date) {
    return !LocalDate.now().isAfter(date);
  }

  @Override
  public LocalDate checkValidOnboardDate(LocalDate onboardDate) {
    if (isValidOnboardDate(onboardDate)) return onboardDate;
    else throw BusinessConstraintException.invalidOnBoardDate();
  }

  @Override
  public HiringRequest checkValidHiringRequestId(Integer id) {
    return null;
  }

  @Override
  public List<HiringRequestDto> findByHiringManagerId(Integer Id) {
    return HiringRequestMapper.INSTANCE.toDtos(hiringRequestRepository.findByHiringManagerId(Id));
  }

  @Override
  public HiringRequestDto update(HiringRequestRequest request, Integer id) {
    HiringRequest updatingHiringRequest =
        hiringRequestRepository
            .findById(id)
            .orElseThrow(EntityNotFoundException::hiringRequestNotFound);
    updatingHiringRequest.setOnBoardingDate(checkValidOnboardDate(request.getOnBoardingDate()));
    updatingHiringRequest.setPosition(request.getPosition());
    updatingHiringRequest.setLevel(request.getLevel());
    updatingHiringRequest.setSpecificBenefit(request.getSpecificBenefit());
    updatingHiringRequest.setDepartment(departmentService.findById(request.getDepartmentId()));
    updatingHiringRequest.setHiringManager(
        employeeService.checkValidHiringManagerId(request.getHiringManagerId()));

    return HiringRequestMapper.INSTANCE.toDto(
        hiringRequestRepository.save(checkValidHiringRequest(updatingHiringRequest)));
  }
}
