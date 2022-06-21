package com.axonactive.personalproject.controller;

import com.axonactive.personalproject.controller.request.ApplicationFormRequest;
import com.axonactive.personalproject.entity.ApplicationForm;
import com.axonactive.personalproject.exception.EntityNotFoundException;
import com.axonactive.personalproject.service.ApplicationFormService;
import com.axonactive.personalproject.service.dto.ApplicationFormDto;
import com.axonactive.personalproject.service.mapper.ApplicationFormMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = ApplicationFormResource.PATH)
@RequiredArgsConstructor
public class ApplicationFormResource {
  public static final String PATH = "/api/application-forms";
  @Autowired ApplicationFormService applicationFormService;

  @PreAuthorize("hasAnyRole('HR','HIRINGMANAGER')")
  @GetMapping
  public ResponseEntity<List<ApplicationFormDto>> getAll(
      @RequestHeader("Authorization") String authorization) {
    return ResponseEntity.ok().body(applicationFormService.findAll());
  }

  @PreAuthorize("hasAnyRole('HR','HIRINGMANAGER')")
  @GetMapping("/{id}")
  public ResponseEntity<ApplicationFormDto> getById(@PathVariable("id") Integer id)
      throws EntityNotFoundException {
    return ResponseEntity.created(URI.create(PATH + "/" + id))
        .body(applicationFormService.findById(id));
  }

  @PreAuthorize("hasRole('HR')")
  @GetMapping("/{id}/salary-expectation")
  public ResponseEntity<Double> getSalary(
      //      @RequestHeader("Authentication") String authentication,
      @PathVariable("id") Integer id) throws EntityNotFoundException {
    return ResponseEntity.ok().body(applicationFormService.getSalary(id));
  }

  @PreAuthorize("hasRole('HR')")
  @GetMapping("/date-range")
  public ResponseEntity<List<ApplicationFormDto>> findBySubmittedDateBetween(
      @RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate) {
    return ResponseEntity.ok()
        .body(applicationFormService.findBySubmittedDateBetween(fromDate, toDate));
  }

  @PreAuthorize("hasRole('HIRINGMANAGER')")
  @GetMapping("/hiring-manager")
  public ResponseEntity<List<ApplicationFormDto>> findByHiringManageInCharge(
      @RequestParam("id") Integer id) {
    return ResponseEntity.ok().body(applicationFormService.findByHiringRequestHiringManagerId(id));
  }

  @GetMapping(value = "/{id}/cv", produces = MediaType.IMAGE_PNG_VALUE)
  public ResponseEntity<byte[]> getCv(@PathVariable("id") Integer id) throws IOException {
    //    String cvPath =
    //    File imgFile = new File(new
    // File("").getAbsolutePath()+File.separator+"src/main/resources/cv/"+fileName);
    //    byte[] bytes = StreamUtils.copyToByteArray(new FileInputStream(imgFile));

    return ResponseEntity.ok()
        .contentType(MediaType.IMAGE_PNG)
        .body(applicationFormService.getCv(id));
  }

  @PostMapping("/{id}/cv")
  public String addCv(@PathVariable("id") Integer id, @RequestParam("file") MultipartFile file)
      throws Exception {
    return applicationFormService.addCv(id, file);
  }

  @PreAuthorize("hasRole('HR')")
  @PostMapping
  public ResponseEntity<ApplicationFormDto> add(@RequestBody ApplicationFormRequest formRequest)
      throws EntityNotFoundException {
    ApplicationForm newForm = applicationFormService.add(formRequest);
    return ResponseEntity.created(URI.create(PATH + "/" + newForm.getId()))
        .body(ApplicationFormMapper.INSTANCE.toDto(newForm));
  }
  //  @GetMapping(produces = MediaType.IMAGE_JPEG_VALUE)
  //  public @ResponseBody byte[] getImageWithMediaType(@RequestParam("code") String code)
  //      throws IOException {
  //    InputStream in = getClass().getResourceAsStream(code);
  //    return IOUtils.toByteArray(in);
  //  }

  @PreAuthorize("hasRole('HR')")
  @PutMapping("/{id}")
  public ResponseEntity<ApplicationFormDto> update(
      @PathVariable("id") Integer id, @RequestBody ApplicationFormRequest updatingRequest)
      throws EntityNotFoundException {

    return ResponseEntity.created(URI.create(PATH + "/" + id))
        .body(applicationFormService.update(id, updatingRequest));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Integer id)
      throws EntityNotFoundException {
    applicationFormService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
