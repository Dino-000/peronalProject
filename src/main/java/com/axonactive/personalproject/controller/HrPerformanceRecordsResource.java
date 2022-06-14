package com.axonactive.personalproject.controller;

import com.axonactive.personalproject.controller.request.HrPerformanceRecordRequest;
import com.axonactive.personalproject.entity.HrPerformanceRecord;
import com.axonactive.personalproject.exception.ResourceNotFoundException;
import com.axonactive.personalproject.service.EmployeeService;
import com.axonactive.personalproject.service.HrPerformanceRecordService;
import com.axonactive.personalproject.service.dto.HrPerformanceRecordDto;
import com.axonactive.personalproject.service.mapper.HrPerformanceRecordMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
@RestController
@RequestMapping(path = HrPerformanceRecordsResource.PATH)
@RequiredArgsConstructor
public class HrPerformanceRecordsResource {
    public static final String PATH ="api/HrPerformanceRecords";
    @Autowired
    HrPerformanceRecordService hrPerformanceRecordService;
    @Autowired
    EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<HrPerformanceRecordDto>> getAll() {
        return ResponseEntity.ok().body(HrPerformanceRecordMapper.INSTANCE.toDtos(hrPerformanceRecordService.findAll()));
    }


    @GetMapping("/{id}")
    public ResponseEntity<HrPerformanceRecordDto> getById (@PathVariable("id") Integer id) throws ResourceNotFoundException {
        HrPerformanceRecord hrPerformanceRecord = hrPerformanceRecordService.findById(id).orElseThrow(()->new ResourceNotFoundException("Can't not find HrPerformanceRecord with that id."));
        return ResponseEntity.created(URI.create(PATH+"/"+hrPerformanceRecord.getId())).body(HrPerformanceRecordMapper.INSTANCE.toDto(hrPerformanceRecord));
    }

    @PostMapping
    public ResponseEntity<HrPerformanceRecordDto> add(
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

        return ResponseEntity.created(URI.create(PATH + "/" + newHrPerformanceRecord.getId())).body(HrPerformanceRecordMapper.INSTANCE.toDto(newHrPerformanceRecord));
    }

    @PutMapping("/{id}")
    public  ResponseEntity<HrPerformanceRecordDto> update(@PathVariable("id") Integer id, @RequestBody HrPerformanceRecordRequest inputData) throws ResourceNotFoundException {
        HrPerformanceRecord updatingHrPerformanceRecord = hrPerformanceRecordService.findById(id).orElseThrow(()->new ResourceNotFoundException("Can't not find HrPerformanceRecord with that id."));
        updatingHrPerformanceRecord.setQuarter(inputData.getQuarter());
        updatingHrPerformanceRecord.setQuarterKpi(inputData.getQuarterKpi());
        updatingHrPerformanceRecord.setQuarterPerformance(inputData.getQuarterPerformance());
        updatingHrPerformanceRecord.setBonus(inputData.getBonus());
        updatingHrPerformanceRecord.setPenalty(inputData.getPenalty());
        updatingHrPerformanceRecord.setPeakRate(inputData.isPeakRate());
        updatingHrPerformanceRecord.setHrOfficer(employeeService.findById(inputData.getHrOfficerId()).get());

        HrPerformanceRecord updatedHrPerformanceRecord = hrPerformanceRecordService.saveHrPerformanceRecord(updatingHrPerformanceRecord);
        return  ResponseEntity.created(URI.create(PATH+"/"+id)).body(HrPerformanceRecordMapper.INSTANCE.toDto(updatedHrPerformanceRecord));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        HrPerformanceRecord deletingHrPerformanceRecord = hrPerformanceRecordService.findById(id).orElseThrow(()->new ResourceNotFoundException("Can't not find HrPerformanceRecord with that id."));
        hrPerformanceRecordService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

