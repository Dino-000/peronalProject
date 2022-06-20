package com.axonactive.personalproject.controller;

import com.axonactive.personalproject.controller.request.WorkingHistoryRecordRequest;
import com.axonactive.personalproject.entity.WorkingHistoryRecord;
import com.axonactive.personalproject.exception.EntityNotFoundException;
import com.axonactive.personalproject.service.WorkingHistoryRecordService;
import com.axonactive.personalproject.service.dto.WorkingHistoryRecordDto;
import com.axonactive.personalproject.service.mapper.WorkingHistoryRecordMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = WorkingHistoryRecordResource.PATH)
@RequiredArgsConstructor
public class WorkingHistoryRecordResource {
  public static final String PATH = "api/working-history-records";
  @Autowired WorkingHistoryRecordService workingHistoryRecordService;

  @GetMapping
  public ResponseEntity<List<WorkingHistoryRecordDto>> getAll() {
    return ResponseEntity.ok().body(workingHistoryRecordService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<WorkingHistoryRecordDto> getById(@PathVariable("id") Integer id)
      throws EntityNotFoundException {
    return ResponseEntity.created(URI.create(PATH + "/" + id))
        .body(workingHistoryRecordService.findById(id));
  }

  @PostMapping
  public ResponseEntity<WorkingHistoryRecordDto> add(
      @RequestBody WorkingHistoryRecordRequest inputData) throws EntityNotFoundException {
    WorkingHistoryRecord newWorkingHistoryRecord = workingHistoryRecordService.add(inputData);

    return ResponseEntity.created(URI.create(PATH + "/" + newWorkingHistoryRecord.getId()))
        .body(WorkingHistoryRecordMapper.INSTANCE.toDto(newWorkingHistoryRecord));
  }

  @PutMapping("/{id}")
  public ResponseEntity<WorkingHistoryRecordDto> update(
      @PathVariable("id") Integer id, @RequestBody WorkingHistoryRecordRequest inputData)
      throws EntityNotFoundException {
    return ResponseEntity.created(URI.create(PATH + "/" + id))
        .body(workingHistoryRecordService.update(inputData, id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Integer id)
      throws EntityNotFoundException {
    workingHistoryRecordService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
