package com.axonactive.personalproject.controller;

import com.axonactive.personalproject.controller.request.WorkingHistoryRecordRequest;
import com.axonactive.personalproject.entity.WorkingHistoryRecord;
import com.axonactive.personalproject.exception.ResourceNotFoundException;
import com.axonactive.personalproject.service.CandidateService;
import com.axonactive.personalproject.service.WorkingHistoryRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
@RestController
@RequestMapping(path = WorkingHistoryRecordController.PATH)
@RequiredArgsConstructor
public class WorkingHistoryRecordController {
    public static final String PATH ="api/WorkingHistoryRecords";
    @Autowired
    WorkingHistoryRecordService workingHistoryRecordService;
    @Autowired
    CandidateService candidateService;

    @GetMapping
    public ResponseEntity<List<WorkingHistoryRecord>> getAll() {
        return ResponseEntity.ok().body(workingHistoryRecordService.findAll());
    }

    @PostMapping
    public ResponseEntity<WorkingHistoryRecord> add(
            @RequestBody WorkingHistoryRecordRequest inputData) {
        WorkingHistoryRecord newWorkingHistoryRecord = workingHistoryRecordService.saveWorkingHistoryRecord(new WorkingHistoryRecord(null,
                inputData.getCompanyName(),
                inputData.getJoinedDate(),
                inputData.getResignationDate(),
                inputData.getPosition(),
                inputData.getProjectName(),
                inputData.getResponsibility(),
                inputData.getClient(),
                inputData.getTeamSize(),
                inputData.getReferencesPeoplePhone(),
                candidateService.findById(inputData.getCandidateId()).get()
        ));

        return ResponseEntity.created(URI.create(PATH + "/" + newWorkingHistoryRecord.getId())).body(newWorkingHistoryRecord);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<WorkingHistoryRecord> update(@PathVariable("id") Integer id, @RequestBody WorkingHistoryRecordRequest inputData) throws ResourceNotFoundException {
        WorkingHistoryRecord updatingWorkingHistoryRecord = workingHistoryRecordService.findById(id).orElseThrow(()->new ResourceNotFoundException("Can't not find Application Form with that id."));
        updatingWorkingHistoryRecord.setCompanyName(inputData.getCompanyName());
        updatingWorkingHistoryRecord.setJoinedDate(inputData.getJoinedDate());
        updatingWorkingHistoryRecord.setResignationDate(inputData.getResignationDate());
        updatingWorkingHistoryRecord.setPosition(inputData.getPosition());
        updatingWorkingHistoryRecord.setProjectName(inputData.getProjectName());
        updatingWorkingHistoryRecord.setResponsibility(inputData.getResponsibility());
        updatingWorkingHistoryRecord.setClient(inputData.getClient());
        updatingWorkingHistoryRecord.setTeamSize(inputData.getTeamSize());
        updatingWorkingHistoryRecord.setReferencesPeoplePhone(inputData.getReferencesPeoplePhone());
        updatingWorkingHistoryRecord.setCandidate( candidateService.findById(inputData.getCandidateId()).get());
        WorkingHistoryRecord updatedWorkingHistoryRecord = workingHistoryRecordService.saveWorkingHistoryRecord(updatingWorkingHistoryRecord);
        return  ResponseEntity.created(URI.create(PATH+"/"+id)).body(updatedWorkingHistoryRecord);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        WorkingHistoryRecord deletingWorkingHistoryRecord = workingHistoryRecordService.findById(id).orElseThrow(()->new ResourceNotFoundException("Can't not find Application Form with that id."));
        workingHistoryRecordService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

