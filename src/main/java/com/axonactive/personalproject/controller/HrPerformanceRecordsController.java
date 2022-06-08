package com.axonactive.personalproject.controller;

import com.axonactive.personalproject.controller.request.HrPerformanceRecordRequest;
import com.axonactive.personalproject.entity.HrPerformanceRecord;
import com.axonactive.personalproject.exception.ResourceNotFoundException;
import com.axonactive.personalproject.service.EmployeeService;
import com.axonactive.personalproject.service.HrPerformanceRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
@RestController
@RequestMapping(path = HrPerformanceRecordsController.PATH)
@RequiredArgsConstructor
public class HrPerformanceRecordsController {
    public static final String PATH ="api/HrPerformanceRecords";
    @Autowired
    HrPerformanceRecordService hrPerformanceRecordService;
    @Autowired
    EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<HrPerformanceRecord>> getAll() {
        return ResponseEntity.ok().body(hrPerformanceRecordService.findAll());
    }

    @PostMapping
    public ResponseEntity<HrPerformanceRecord> add(
            @RequestBody HrPerformanceRecordRequest inputData) {
        HrPerformanceRecord newHrPerformanceRecord = hrPerformanceRecordService.saveHrPerformanceRecord(new HrPerformanceRecord(null,
                inputData.getQuarter(),
                inputData.getQuarterKpi(),
                inputData.getQuarterPerformance(),
                inputData.getBonus(),
                inputData.getPenalty(),
                inputData.isPeakRate(),
                employeeService.findById(inputData.getHrOfficerId()).get()
        ));

        return ResponseEntity.created(URI.create(PATH + "/" + newHrPerformanceRecord.getId())).body(newHrPerformanceRecord);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<HrPerformanceRecord> update(@PathVariable("id") Integer id, @RequestBody HrPerformanceRecordRequest inputData) throws ResourceNotFoundException {
        HrPerformanceRecord updatingHrPerformanceRecord = hrPerformanceRecordService.findById(id).orElseThrow(()->new ResourceNotFoundException("Can't not find Application Form with that id."));
        updatingHrPerformanceRecord.setQuarter(inputData.getQuarter());
        updatingHrPerformanceRecord.setQuarterKpi(inputData.getQuarterKpi());
        updatingHrPerformanceRecord.setQuarterPerformance(inputData.getQuarterPerformance());
        updatingHrPerformanceRecord.setBonus(inputData.getBonus());
        updatingHrPerformanceRecord.setPenalty(inputData.getPenalty());
        updatingHrPerformanceRecord.setPeakRate(inputData.isPeakRate());
        updatingHrPerformanceRecord.setHrOfficer(employeeService.findById(inputData.getHrOfficerId()).get());

        HrPerformanceRecord updatedHrPerformanceRecord = hrPerformanceRecordService.saveHrPerformanceRecord(updatingHrPerformanceRecord);
        return  ResponseEntity.created(URI.create(PATH+"/"+id)).body(updatedHrPerformanceRecord);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        HrPerformanceRecord deletingHrPerformanceRecord = hrPerformanceRecordService.findById(id).orElseThrow(()->new ResourceNotFoundException("Can't not find Application Form with that id."));
        hrPerformanceRecordService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

