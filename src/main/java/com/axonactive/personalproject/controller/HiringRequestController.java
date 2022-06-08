package com.axonactive.personalproject.controller;

import com.axonactive.personalproject.controller.request.HiringRequestRequest;
import com.axonactive.personalproject.entity.HiringRequest;
import com.axonactive.personalproject.exception.ResourceNotFoundException;
import com.axonactive.personalproject.service.DepartmentService;
import com.axonactive.personalproject.service.EmployeeService;
import com.axonactive.personalproject.service.HiringRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = HiringRequestController.PATH)
@RequiredArgsConstructor
public class HiringRequestController {
    public static final String PATH ="api/HiringRequests";
    @Autowired
    HiringRequestService hiringRequestService;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    EmployeeService employeeService;



    @GetMapping
    public ResponseEntity<List<HiringRequest>> getAll() {
        return ResponseEntity.ok().body(hiringRequestService.findAll());
    }

    @PostMapping
    public ResponseEntity<HiringRequest> add(
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

        return ResponseEntity.created(URI.create(PATH + "/" + newHiringRequest.getId())).body(newHiringRequest);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<HiringRequest> update(@PathVariable("id") Integer id, @RequestBody HiringRequestRequest inputData) throws ResourceNotFoundException {
        HiringRequest updatingHiringRequest = hiringRequestService.findById(id).orElseThrow(()->new ResourceNotFoundException("Can't not find Application Form with that id."));
        updatingHiringRequest.setOnBoardingDate(inputData.getOnBoardingDate());
        updatingHiringRequest.setPosition(inputData.getPosition());
        updatingHiringRequest.setSpecificBenefit(inputData.getSpecificBenefit());
        updatingHiringRequest.setBonusPoint(inputData.getBonusPoint());
        updatingHiringRequest.setDepartment(departmentService.findById(inputData.getDepartmentId()).get());
        updatingHiringRequest.setHiringManager(employeeService.findById(inputData.getHiringManagerId()).get());
        updatingHiringRequest.setHrOfficer( employeeService.findById(inputData.getHrOfficerId()).get());
        HiringRequest updatedHiringRequest = hiringRequestService.saveHiringRequest(updatingHiringRequest);
        return  ResponseEntity.created(URI.create(PATH+"/"+id)).body(updatedHiringRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        HiringRequest deletingHiringRequest = hiringRequestService.findById(id).orElseThrow(()->new ResourceNotFoundException("Can't not find Application Form with that id."));
        hiringRequestService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

