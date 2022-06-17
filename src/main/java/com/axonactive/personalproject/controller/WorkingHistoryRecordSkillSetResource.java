package com.axonactive.personalproject.controller;

import com.axonactive.personalproject.controller.request.WorkingHistoryRecordSkillSetRequest;
import com.axonactive.personalproject.entity.WorkingHistoryRecordSkillSet;
import com.axonactive.personalproject.exception.ResourceNotFoundException;
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
  public static final String PATH = "api/working-history-records-skillsets";
  @Autowired WorkingHistoryRecordSkillSetService workingHistoryRecordSkillSetService;

  @GetMapping
  public ResponseEntity<List<WorkingHistoryRecordSkillSetDto>> getAll() {
    return ResponseEntity.ok().body(workingHistoryRecordSkillSetService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<WorkingHistoryRecordSkillSetDto> getById(@PathVariable("id") Integer id)
      throws ResourceNotFoundException {
    return ResponseEntity.created(URI.create(PATH + "/" + id))
        .body(workingHistoryRecordSkillSetService.findById(id));
  }

  @PostMapping
  public ResponseEntity<WorkingHistoryRecordSkillSetDto> add(
      @RequestBody WorkingHistoryRecordSkillSetRequest inputData) throws ResourceNotFoundException {
    WorkingHistoryRecordSkillSet newWorkingHistoryRecordSkillSet =
        workingHistoryRecordSkillSetService.add(inputData);

    return ResponseEntity.created(URI.create(PATH + "/" + newWorkingHistoryRecordSkillSet.getId()))
        .body(WorkingHistoryRecordSkillSetMapper.INSTANCE.toDto(newWorkingHistoryRecordSkillSet));
  }

  @PutMapping("/{id}")
  public ResponseEntity<WorkingHistoryRecordSkillSetDto> update(
      @PathVariable("id") Integer id, @RequestBody WorkingHistoryRecordSkillSetRequest inputData)
      throws ResourceNotFoundException {

    return ResponseEntity.created(URI.create(PATH + "/" + id))
        .body(workingHistoryRecordSkillSetService.update(inputData, id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Integer id)
      throws ResourceNotFoundException {
    workingHistoryRecordSkillSetService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
