package com.axonactive.personalproject.controller;

import com.axonactive.personalproject.entity.SkillSet;
import com.axonactive.personalproject.exception.ResourceNotFoundException;
import com.axonactive.personalproject.service.SkillSetService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(SkillSetResource.PATH)
@RequiredArgsConstructor
public class SkillSetResource {
  public static final String PATH = "api/skillsets";
  @Autowired SkillSetService skillSetService;

  @GetMapping
  public ResponseEntity<List<SkillSet>> getAll() {
    return ResponseEntity.ok().body(skillSetService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<SkillSet> getById(@PathVariable("id") Integer id)
      throws ResourceNotFoundException {
    return ResponseEntity.created(URI.create(PATH + "/" + id)).body(skillSetService.findById(id));
  }

  @PostMapping
  public ResponseEntity<SkillSet> add(@RequestBody SkillSet inputData) {
    SkillSet newSkillSet = skillSetService.add(inputData);

    return ResponseEntity.created(URI.create(PATH + "/" + newSkillSet.getId())).body(newSkillSet);
  }

  @PutMapping("/{id}")
  public ResponseEntity<SkillSet> update(
      @PathVariable("id") Integer id, @RequestBody SkillSet updateDetail)
      throws ResourceNotFoundException {
    return ResponseEntity.created(URI.create(PATH + "/" + id))
        .body(skillSetService.update(updateDetail, id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Integer id)
      throws ResourceNotFoundException {
    skillSetService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
