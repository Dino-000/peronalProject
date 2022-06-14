package com.axonactive.personalproject.controller;

import com.axonactive.personalproject.controller.request.HiringRequestRequest;
import com.axonactive.personalproject.entity.HiringRequest;
import com.axonactive.personalproject.exception.ResourceNotFoundException;
import com.axonactive.personalproject.service.DepartmentService;
import com.axonactive.personalproject.service.EmployeeService;
import com.axonactive.personalproject.service.HiringRequestService;
import com.axonactive.personalproject.service.dto.HiringRequestDto;
import com.axonactive.personalproject.service.mapper.HiringRequestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = HiringRequestResource.PATH)
@RequiredArgsConstructor
public class HiringRequestResource {
    public static final String PATH ="api/HiringRequests";
    @Autowired
    HiringRequestService hiringRequestService;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    EmployeeService employeeService;



    @GetMapping
    public ResponseEntity<List<HiringRequestDto>> getAll() {
        return ResponseEntity.ok().body(HiringRequestMapper.INSTANCE.toDtos(hiringRequestService.findAll()));
    }


    @GetMapping("/{id}")
    public ResponseEntity<HiringRequestDto> getById (@PathVariable("id") Integer id) throws ResourceNotFoundException {
        HiringRequest hiringRequest = hiringRequestService.findById(id).orElseThrow(()->new ResourceNotFoundException("Can't not find HiringRequest with that id."));
        return ResponseEntity.created(URI.create(PATH+"/"+hiringRequest.getId())).body(HiringRequestMapper.INSTANCE.toDto(hiringRequest));
    }
    @PostMapping
    public ResponseEntity<HiringRequestDto> add(
            @RequestBody HiringRequestRequest inputData) {
        HiringRequest newHiringRequest = hiringRequestService.saveHiringRequest(new HiringRequest(null,
               inputData.getOnBoardingDate(),
                inputData.getPosition(),
                inputData.getSpecificBenefit(),
                inputData.getBonusPoint(),
                departmentService.findById(inputData.getDepartmentId()).get(),
                employeeService.findById(inputData.getHiringManagerId()).get(),
                employeeService.findById(inputData.getHrOfficerId()).get()
        ));

        return ResponseEntity.created(URI.create(PATH + "/" + newHiringRequest.getId())).body(HiringRequestMapper.INSTANCE.toDto(newHiringRequest));
    }

    @PutMapping("/{id}")
    public  ResponseEntity<HiringRequestDto> update(@PathVariable("id") Integer id, @RequestBody HiringRequestRequest inputData) throws ResourceNotFoundException {
        HiringRequest updatingHiringRequest = hiringRequestService.findById(id).orElseThrow(()->new ResourceNotFoundException("Can't not find HiringRequest with that id."));
        updatingHiringRequest.setOnBoardingDate(inputData.getOnBoardingDate());
        updatingHiringRequest.setPosition(inputData.getPosition());
        updatingHiringRequest.setSpecificBenefit(inputData.getSpecificBenefit());
        updatingHiringRequest.setBonusPoint(inputData.getBonusPoint());
        updatingHiringRequest.setDepartment(departmentService.findById(inputData.getDepartmentId()).get());
        updatingHiringRequest.setHiringManager(employeeService.findById(inputData.getHiringManagerId()).get());
        updatingHiringRequest.setHrOfficer( employeeService.findById(inputData.getHrOfficerId()).get());
        HiringRequest updatedHiringRequest = hiringRequestService.saveHiringRequest(updatingHiringRequest);
        return  ResponseEntity.created(URI.create(PATH+"/"+id)).body(HiringRequestMapper.INSTANCE.toDto(updatedHiringRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        HiringRequest deletingHiringRequest = hiringRequestService.findById(id).orElseThrow(()->new ResourceNotFoundException("Can't not find HiringRequest with that id."));
        hiringRequestService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

