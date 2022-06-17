package com.axonactive.personalproject.controller;

import com.axonactive.personalproject.controller.request.HiringRequestSkillSetRequest;
import com.axonactive.personalproject.entity.HiringRequestSkillSet;
import com.axonactive.personalproject.exception.ResourceNotFoundException;
import com.axonactive.personalproject.service.HiringRequestSkillSetService;
import com.axonactive.personalproject.service.dto.HiringRequestSkillSetDto;
import com.axonactive.personalproject.service.mapper.HiringRequestSkillSetMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = HiringRequestSkillSetResource.PATH)
@RequiredArgsConstructor
public class HiringRequestSkillSetResource {
  public static final String PATH = "api/hiringrequest-skillsets";
  @Autowired HiringRequestSkillSetService hiringRequestSkillSetService;

  @GetMapping
  public ResponseEntity<List<HiringRequestSkillSetDto>> getAll() {
    return ResponseEntity.ok().body(hiringRequestSkillSetService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<HiringRequestSkillSetDto> getById(@PathVariable("id") Integer id)
      throws ResourceNotFoundException {
    return ResponseEntity.created(URI.create(PATH + "/" + id))
        .body(hiringRequestSkillSetService.findById(id));
  }

  @PostMapping
  public ResponseEntity<HiringRequestSkillSetDto> add(
      @RequestBody HiringRequestSkillSetRequest inputData) throws ResourceNotFoundException {
    HiringRequestSkillSet newHiringRequestSkillSet = hiringRequestSkillSetService.add(inputData);

    return ResponseEntity.created(URI.create(PATH + "/" + newHiringRequestSkillSet.getId()))
        .body(HiringRequestSkillSetMapper.INSTANCE.toDto(newHiringRequestSkillSet));
  }

  @PutMapping("/{id}")
  public ResponseEntity<HiringRequestSkillSetDto> update(
      @PathVariable("id") Integer id, @RequestBody HiringRequestSkillSetRequest inputData)
      throws ResourceNotFoundException {

    return ResponseEntity.created(URI.create(PATH + "/" + id))
        .body(hiringRequestSkillSetService.update(inputData, id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Integer id)
      throws ResourceNotFoundException {

    hiringRequestSkillSetService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
