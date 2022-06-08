package com.axonactive.personalproject.controller;

import com.axonactive.personalproject.controller.request.WorkingHistoryRecordSkillSetRequest;
import com.axonactive.personalproject.entity.WorkingHistoryRecordSkillSet;
import com.axonactive.personalproject.exception.ResourceNotFoundException;
import com.axonactive.personalproject.service.SkillSetService;
import com.axonactive.personalproject.service.WorkingHistoryRecordSkillSetService;
import com.axonactive.personalproject.service.WorkingHistoryRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
@RestController
@RequestMapping(path = WorkingHistoryRecordSkillSetController.PATH)
@RequiredArgsConstructor
public class WorkingHistoryRecordSkillSetController {
    public static final String PATH ="api/WorkingHistoryRecordSkillSets";
    @Autowired
    WorkingHistoryRecordSkillSetService workingHistoryRecordSkillSetService;
    @Autowired
    WorkingHistoryRecordService workingHistoryRecordService;
    @Autowired
    SkillSetService skillSetService;

    @GetMapping
    public ResponseEntity<List<WorkingHistoryRecordSkillSet>> getAll() {
        return ResponseEntity.ok().body(workingHistoryRecordSkillSetService.findAll());
    }

    @PostMapping
    public ResponseEntity<WorkingHistoryRecordSkillSet> add(
            @RequestBody WorkingHistoryRecordSkillSetRequest inputData) {
        WorkingHistoryRecordSkillSet newWorkingHistoryRecordSkillSet = workingHistoryRecordSkillSetService.saveWorkingHistoryRecordSkillSet(new WorkingHistoryRecordSkillSet(null,
                workingHistoryRecordService.findById(inputData.getWorkingHistoryRecordId()).get(),
                skillSetService.findById(inputData.getSkillSetId()).get()
                ));

        return ResponseEntity.created(URI.create(PATH + "/" + newWorkingHistoryRecordSkillSet.getId())).body(newWorkingHistoryRecordSkillSet);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<WorkingHistoryRecordSkillSet> update(@PathVariable("id") Integer id, @RequestBody WorkingHistoryRecordSkillSetRequest inputData) throws ResourceNotFoundException {
        WorkingHistoryRecordSkillSet updatingWorkingHistoryRecordSkillSet = workingHistoryRecordSkillSetService.findById(id).orElseThrow(()->new ResourceNotFoundException("Can't not find Application Form with that id."));
        updatingWorkingHistoryRecordSkillSet.setSkillSet(skillSetService.findById(inputData.getSkillSetId()).get());
        updatingWorkingHistoryRecordSkillSet.setWorkingHistoryRecord(workingHistoryRecordService.findById(inputData.getWorkingHistoryRecordId()).get());
        WorkingHistoryRecordSkillSet updatedWorkingHistoryRecordSkillSet = workingHistoryRecordSkillSetService.saveWorkingHistoryRecordSkillSet(updatingWorkingHistoryRecordSkillSet);
        return  ResponseEntity.created(URI.create(PATH+"/"+id)).body(updatedWorkingHistoryRecordSkillSet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        WorkingHistoryRecordSkillSet deletingWorkingHistoryRecordSkillSet = workingHistoryRecordSkillSetService.findById(id).orElseThrow(()->new ResourceNotFoundException("Can't not find Application Form with that id."));
        workingHistoryRecordSkillSetService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

