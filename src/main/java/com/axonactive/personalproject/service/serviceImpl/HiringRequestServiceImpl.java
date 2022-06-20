package com.axonactive.personalproject.service.serviceImpl;

import com.axonactive.personalproject.controller.request.HiringRequestRequest;
import com.axonactive.personalproject.entity.HiringRequest;
import com.axonactive.personalproject.exception.BusinessConstraintException;
import com.axonactive.personalproject.exception.ResourceNotFoundException;
import com.axonactive.personalproject.repository.DepartmentRepository;
import com.axonactive.personalproject.repository.EmployeeRepository;
import com.axonactive.personalproject.repository.HiringRequestRepository;
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
  @Autowired DepartmentRepository departmentRepository;
  @Autowired EmployeeRepository employeeRepository;

  @Override
  public List<HiringRequestDto> findAll() {
    return HiringRequestMapper.INSTANCE.toDtos(hiringRequestRepository.findAll());
  }

  @Override
  public HiringRequestDto findById(Integer id) throws ResourceNotFoundException {
    return HiringRequestMapper.INSTANCE.toDto(
        hiringRequestRepository
            .findById(id)
            .orElseThrow(ResourceNotFoundException::hiringRequestNotFound));
  }

  @Override
  public void deleteById(Integer id) throws ResourceNotFoundException {
    findById(id);
    hiringRequestRepository.deleteById(id);
  }

  @Override
  public HiringRequest add(HiringRequestRequest request) throws ResourceNotFoundException {
    HiringRequest hiringRequest = convertRequestToEntity(request);
    if (!isHiringManager(hiringRequest)) {
      log.info("The Hiring Manger's Department: " + hiringRequest.getDepartment().getName());
      log.info("The Hiring Manager Id: " + hiringRequest.getHiringManager().getEmployeeId());
      log.info("This Department's Manager Id: " + hiringRequest.getDepartment().getManagerID());
      throw BusinessConstraintException.invalidHiringManagerOfficer();
    } else if (!isValidHiringManager(hiringRequest)) {
      log.info(
          "The Hiring Manger's Department: "
              + hiringRequest.getHiringManager().getDepartment().getName());
      log.info("This Hiring Request's Department: " + hiringRequest.getDepartment().getName());
      throw BusinessConstraintException.hiringManagerDepartmentMissMatch();
    }
    if (!isValidOnboardDate(request.getOnBoardingDate())) {
      log.info("The On-boarding Date is: " + hiringRequest.getOnBoardingDate());
      throw BusinessConstraintException.invalidOnBoardDate();
    }
    return hiringRequestRepository.save(convertRequestToEntity(request));
  }

  @Override
  public HiringRequest convertRequestToEntity(HiringRequestRequest request)
      throws ResourceNotFoundException {
    return new HiringRequest(
        null,
        request.getOnBoardingDate(),
        request.getPosition(),
        request.getLevel(),
        request.getSpecificBenefit(),
        request.getBudget(),
        departmentRepository
            .findById(request.getDepartmentId())
            .orElseThrow(ResourceNotFoundException::departmentNotFound),
        employeeRepository
            .findById(request.getHiringManagerId())
            .orElseThrow(ResourceNotFoundException::employeeNotFound));
  }

  @Override
  public boolean isValidHiringManager(HiringRequest request) {
    return request
        .getHiringManager()
        .getDepartment()
        .getName()
        .equals(request.getDepartment().getName());
  }

  @Override
  public boolean isHiringManager(HiringRequest request) {

    return !(request.getHiringManager().getDepartment().getName().equals("HR"))
        || (request.getHiringManager().getDepartment().getName().equals("HR")
            && request
                .getHiringManager()
                .getDepartment()
                .getManagerID()
                .equals(request.getHiringManager().getEmployeeId()));
  }

  @Override
  public boolean isValidOnboardDate(LocalDate date) {
    return !LocalDate.now().isAfter(date);
  }

  @Override
  public List<HiringRequestDto> findByHiringManagerId(Integer Id) {
    return HiringRequestMapper.INSTANCE.toDtos(hiringRequestRepository.findByHiringManagerId(Id));
  }

  @Override
  public HiringRequestDto update(HiringRequestRequest request, Integer id)
      throws ResourceNotFoundException {
    HiringRequest updatingHiringRequest =
        hiringRequestRepository
            .findById(id)
            .orElseThrow(ResourceNotFoundException::hiringRequestNotFound);
    updatingHiringRequest.setOnBoardingDate(request.getOnBoardingDate());
    updatingHiringRequest.setPosition(request.getPosition());
    updatingHiringRequest.setLevel(request.getLevel());
    updatingHiringRequest.setSpecificBenefit(request.getSpecificBenefit());
    updatingHiringRequest.setDepartment(
        departmentRepository
            .findById(request.getDepartmentId())
            .orElseThrow(ResourceNotFoundException::departmentNotFound));
    updatingHiringRequest.setHiringManager(
        employeeRepository
            .findById(request.getHiringManagerId())
            .orElseThrow(ResourceNotFoundException::employeeNotFound));

    if (!isHiringManager(updatingHiringRequest)) {
      log.info(
          "The Hiring Manger's Department: " + updatingHiringRequest.getDepartment().getName());
      log.info(
          "The Hiring Manager Id: " + updatingHiringRequest.getHiringManager().getEmployeeId());
      log.info(
          "This Department's Manager Id: " + updatingHiringRequest.getDepartment().getManagerID());
      throw BusinessConstraintException.invalidHiringManagerOfficer();
    } else if (!isValidHiringManager(updatingHiringRequest)) {
      log.info(
          "The Hiring Manger's Department: "
              + updatingHiringRequest.getHiringManager().getDepartment().getName());
      log.info(
          "This Hiring Request's Department: " + updatingHiringRequest.getDepartment().getName());
      throw BusinessConstraintException.hiringManagerDepartmentMissMatch();
    }
    if (!isValidOnboardDate(request.getOnBoardingDate())) {
      log.info("The On-boarding Date is: " + updatingHiringRequest.getOnBoardingDate());
      throw BusinessConstraintException.invalidOnBoardDate();
    }
    return HiringRequestMapper.INSTANCE.toDto(hiringRequestRepository.save(updatingHiringRequest));
  }
}
