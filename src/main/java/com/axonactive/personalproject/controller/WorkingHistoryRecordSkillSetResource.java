package com.axonactive.personalproject.controller;

import com.axonactive.personalproject.controller.request.WorkingHistoryRecordSkillSetRequest;
import com.axonactive.personalproject.entity.WorkingHistoryRecordSkillSet;
import com.axonactive.personalproject.exception.ResourceNotFoundException;
import com.axonactive.personalproject.service.SkillSetService;
import com.axonactive.personalproject.service.WorkingHistoryRecordService;
import com.axonactive.personalproject.service.WorkingHistoryRecordSkillSetService;
import com.axonactive.personalproject.service.dto.WorkingHistoryRecordSkillSetDto;
import com.axonactive.personalproject.service.mapper.WorkingHistoryRecordSkillSetMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
@RestController
@RequestMapping(path = WorkingHistoryRecordSkillSetResource.PATH)
@RequiredArgsConstructor
public class WorkingHistoryRecordSkillSetResource {
    public static final String PATH ="api/working-history-records-skillsets";
    @Autowired
    WorkingHistoryRecordSkillSetService workingHistoryRecordSkillSetService;
    @Autowired
    WorkingHistoryRecordService workingHistoryRecordService;
    @Autowired
    SkillSetService skillSetService;

    @GetMapping
    public ResponseEntity<List<WorkingHistoryRecordSkillSetDto>> getAll() {
        return ResponseEntity.ok().body(WorkingHistoryRecordSkillSetMapper.INSTANCE.toDtos(workingHistoryRecordSkillSetService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkingHistoryRecordSkillSetDto> getById (@PathVariable("id") Integer id) throws ResourceNotFoundException {
        WorkingHistoryRecordSkillSet workingHistoryRecordSkillSet = workingHistoryRecordSkillSetService.findById(id).orElseThrow(()->new ResourceNotFoundException("Can't not find Working History Record SkillSet with that id."));
        return ResponseEntity.created(URI.create(PATH + "/" + workingHistoryRecordSkillSet.getId())).body(WorkingHistoryRecordSkillSetMapper.INSTANCE.toDto(workingHistoryRecordSkillSet));
    }

    @PostMapping
    public ResponseEntity<WorkingHistoryRecordSkillSetDto> add(
            @RequestBody WorkingHistoryRecordSkillSetRequest inputData) {
        WorkingHistoryRecordSkillSet newWorkingHistoryRecordSkillSet = workingHistoryRecordSkillSetService.saveWorkingHistoryRecordSkillSet(new WorkingHistoryRecordSkillSet(null,
                workingHistoryRecordService.findById(inputData.getWorkingHistoryRecordId()).get(),
                skillSetService.findById(inputData.getSkillSetId()).get()
                ));

        return ResponseEntity.created(URI.create(PATH + "/" + newWorkingHistoryRecordSkillSet.getId())).body(WorkingHistoryRecordSkillSetMapper.INSTANCE.toDto(newWorkingHistoryRecordSkillSet));
    }

    @PutMapping("/{id}")
    public  ResponseEntity<WorkingHistoryRecordSkillSetDto> update(@PathVariable("id") Integer id, @RequestBody WorkingHistoryRecordSkillSetRequest inputData) throws ResourceNotFoundException {
        WorkingHistoryRecordSkillSet updatingWorkingHistoryRecordSkillSet = workingHistoryRecordSkillSetService.findById(id).orElseThrow(()->new ResourceNotFoundException("Can't not find Working History Record SkillSet with that id."));
        updatingWorkingHistoryRecordSkillSet.setSkillSet(skillSetService.findById(inputData.getSkillSetId()).get());
        updatingWorkingHistoryRecordSkillSet.setWorkingHistoryRecord(workingHistoryRecordService.findById(inputData.getWorkingHistoryRecordId()).get());
        WorkingHistoryRecordSkillSet updatedWorkingHistoryRecordSkillSet = workingHistoryRecordSkillSetService.saveWorkingHistoryRecordSkillSet(updatingWorkingHistoryRecordSkillSet);
        return  ResponseEntity.created(URI.create(PATH+"/"+id)).body(WorkingHistoryRecordSkillSetMapper.INSTANCE.toDto(updatedWorkingHistoryRecordSkillSet));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        WorkingHistoryRecordSkillSet deletingWorkingHistoryRecordSkillSet = workingHistoryRecordSkillSetService.findById(id).orElseThrow(()->new ResourceNotFoundException("Can't not find Working History Record SkillSet with that id."));
        workingHistoryRecordSkillSetService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

