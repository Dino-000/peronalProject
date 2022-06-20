package com.axonactive.personalproject.controller;

import com.axonactive.personalproject.entity.Certification;
import com.axonactive.personalproject.exception.EntityNotFoundException;
import com.axonactive.personalproject.service.CertificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = CertificationResource.PATH)
@RequiredArgsConstructor
public class CertificationResource {
  public static final String PATH = "api/certifications";
  @Autowired CertificationService certificationService;

  @GetMapping
  public ResponseEntity<List<Certification>> getAll() {
    return ResponseEntity.ok().body(certificationService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Certification> getById(@PathVariable("id") Integer id)
      throws EntityNotFoundException {

    return ResponseEntity.created(URI.create(PATH + "/" + id))
        .body(certificationService.findById(id));
  }

  @PostMapping
  public ResponseEntity<Certification> add(@RequestBody Certification inputData) {
    Certification newCertification = certificationService.add(inputData);
    inputData.getType();

    return ResponseEntity.created(URI.create(PATH + "/" + newCertification.getId()))
        .body(newCertification);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Certification> update(
      @PathVariable("id") Integer id, @RequestBody Certification inputData)
      throws EntityNotFoundException {
    return ResponseEntity.created(URI.create(PATH + "/" + id))
        .body(certificationService.update(inputData, id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Integer id)
      throws EntityNotFoundException {
    certificationService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
