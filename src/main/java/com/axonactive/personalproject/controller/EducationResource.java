package com.axonactive.personalproject.controller;

import com.axonactive.personalproject.entity.Education;
import com.axonactive.personalproject.exception.EntityNotFoundException;
import com.axonactive.personalproject.service.EducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = EducationResource.PATH)
@RequiredArgsConstructor
public class EducationResource {
  public static final String PATH = "api/educations";
  @Autowired EducationService educationService;

  @GetMapping
  public ResponseEntity<List<Education>> getAll() {
    return ResponseEntity.ok().body(educationService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Education> getById(@PathVariable("id") Integer id)
      throws EntityNotFoundException {
    return ResponseEntity.created(URI.create(PATH + "/" + id)).body(educationService.findById(id));
  }

  @PostMapping
  public ResponseEntity<Education> add(@RequestBody Education inputData) {
    Education newEducation = educationService.add(inputData);

    return ResponseEntity.created(URI.create(PATH + "/" + newEducation.getId())).body(newEducation);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Education> update(
      @PathVariable("id") Integer id, @RequestBody Education inputData)
      throws EntityNotFoundException {

    return ResponseEntity.created(URI.create(PATH + "/" + id))
        .body(educationService.update(inputData, id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Integer id)
      throws EntityNotFoundException {
    educationService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
