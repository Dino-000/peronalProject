package com.axonactive.personalproject.controller;

import com.axonactive.personalproject.entity.RecruitmentChanel;
import com.axonactive.personalproject.exception.ResourceNotFoundException;
import com.axonactive.personalproject.service.RecruitmentChanelService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = RecruitmentChanelResource.PATH)
@RequiredArgsConstructor
public class RecruitmentChanelResource {
  public static final String PATH = "api/recruitment-channels";
  @Autowired RecruitmentChanelService recruitmentChanelService;

  @GetMapping
  public ResponseEntity<List<RecruitmentChanel>> getAll() {
    return ResponseEntity.ok().body(recruitmentChanelService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<RecruitmentChanel> getById(@PathVariable("id") Integer id)
      throws ResourceNotFoundException {
    return ResponseEntity.created(URI.create(PATH + "/" + id))
        .body(recruitmentChanelService.findById(id));
  }

  @PostMapping
  public ResponseEntity<RecruitmentChanel> add(@RequestBody RecruitmentChanel inputData) {
    RecruitmentChanel newRecruitmentChanel = recruitmentChanelService.add(inputData);

    return ResponseEntity.created(URI.create(PATH + "/" + newRecruitmentChanel.getId()))
        .body(newRecruitmentChanel);
  }

  @PutMapping("/{id}")
  public ResponseEntity<RecruitmentChanel> update(
      @PathVariable("id") Integer id, @RequestBody RecruitmentChanel inputData)
      throws ResourceNotFoundException {

    return ResponseEntity.created(URI.create(PATH + "/" + id))
        .body(recruitmentChanelService.update(inputData, id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Integer id)
      throws ResourceNotFoundException {

    recruitmentChanelService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
