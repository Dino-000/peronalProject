package com.axonactive.personalproject.service.serviceImpl;

import com.axonactive.personalproject.controller.request.HiringRequestRequest;
import com.axonactive.personalproject.entity.HiringRequest;
import com.axonactive.personalproject.exception.ResourceNotFoundException;
import com.axonactive.personalproject.repository.DepartmentRepository;
import com.axonactive.personalproject.repository.EmployeeRepository;
import com.axonactive.personalproject.repository.HiringRequestRepository;
import com.axonactive.personalproject.service.HiringRequestService;
import com.axonactive.personalproject.service.dto.HiringRequestDto;
import com.axonactive.personalproject.service.mapper.HiringRequestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
            .orElseThrow(
                () -> new ResourceNotFoundException("Can't not find HiringRequest with that id.")));
  }

  @Override
  public void deleteById(Integer id) throws ResourceNotFoundException {
    findById(id);
    hiringRequestRepository.deleteById(id);
  }

  @Override
  public HiringRequest add(HiringRequestRequest request) throws ResourceNotFoundException {
    return hiringRequestRepository.save(convertRequestToDto(request));
  }

  @Override
  public HiringRequest convertRequestToDto(HiringRequestRequest request)
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
            .orElseThrow(
                () -> new ResourceNotFoundException("Can't not find Department with that id.")),
        employeeRepository
            .findById(request.getHiringManagerId())
            .orElseThrow(
                () -> new ResourceNotFoundException("Can't not find Employee with that id.")));
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
            .orElseThrow(
                () -> new ResourceNotFoundException("Can't not find HiringRequest with that id."));
    updatingHiringRequest.setOnBoardingDate(request.getOnBoardingDate());
    updatingHiringRequest.setPosition(request.getPosition());
    updatingHiringRequest.setLevel(request.getLevel());
    updatingHiringRequest.setSpecificBenefit(request.getSpecificBenefit());
    updatingHiringRequest.setDepartment(
        departmentRepository
            .findById(request.getDepartmentId())
            .orElseThrow(
                () -> new ResourceNotFoundException("Can't not find Department with that id.")));
    updatingHiringRequest.setHiringManager(
        employeeRepository
            .findById(request.getHiringManagerId())
            .orElseThrow(
                () -> new ResourceNotFoundException("Can't not find Employee with that id.")));
    return HiringRequestMapper.INSTANCE.toDto(hiringRequestRepository.save(updatingHiringRequest));
  }
}
