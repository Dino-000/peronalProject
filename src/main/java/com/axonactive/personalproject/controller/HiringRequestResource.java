package com.axonactive.personalproject.controller;

import com.axonactive.personalproject.controller.request.HiringRequestRequest;
import com.axonactive.personalproject.entity.HiringRequest;
import com.axonactive.personalproject.exception.EntityNotFoundException;
import com.axonactive.personalproject.service.HiringRequestService;
import com.axonactive.personalproject.service.dto.HiringRequestDto;
import com.axonactive.personalproject.service.mapper.HiringRequestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = HiringRequestResource.PATH)
@RequiredArgsConstructor
public class HiringRequestResource {
  public static final String PATH = "api/hiringrequests";
  @Autowired HiringRequestService hiringRequestService;

  @GetMapping
  public ResponseEntity<List<HiringRequestDto>> getAll() {
    return ResponseEntity.ok().body(hiringRequestService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<HiringRequestDto> getById(@PathVariable("id") Integer id)
      throws EntityNotFoundException {
    return ResponseEntity.created(URI.create(PATH + "/" + id))
        .body(hiringRequestService.findById(id));
  }

  @GetMapping("/hiring-manager/{id}")
  public ResponseEntity<List<HiringRequestDto>> findByHiringManagerId(
      @PathVariable("id") Integer id) {
    return ResponseEntity.ok().body(hiringRequestService.findByHiringManagerId(id));
  }

  @PostMapping
  public ResponseEntity<HiringRequestDto> add(@RequestBody HiringRequestRequest inputData)
      throws EntityNotFoundException {
    HiringRequest newHiringRequest = hiringRequestService.add(inputData);

    return ResponseEntity.created(URI.create(PATH + "/" + newHiringRequest.getId()))
        .body(HiringRequestMapper.INSTANCE.toDto(newHiringRequest));
  }

  @PutMapping("/{id}")
  public ResponseEntity<HiringRequestDto> update(
      @PathVariable("id") Integer id, @RequestBody HiringRequestRequest inputData)
      throws EntityNotFoundException {

    return ResponseEntity.created(URI.create(PATH + "/" + id))
        .body(hiringRequestService.update(inputData, id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Integer id)
      throws EntityNotFoundException {

    hiringRequestService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
