package com.axonactive.personalproject.controller;

import com.axonactive.personalproject.controller.request.CandidateCertificationRequest;
import com.axonactive.personalproject.entity.CandidateCertification;
import com.axonactive.personalproject.exception.EntityNotFoundException;
import com.axonactive.personalproject.service.CandidateCertificationService;
import com.axonactive.personalproject.service.dto.CandidateCertificationDto;
import com.axonactive.personalproject.service.mapper.CandidateCertificationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@PreAuthorize("hasRole('ROLE_HIRINGMANAGER')")

@RestController
@RequestMapping(CandidateCertificationResource.PATH)
@RequiredArgsConstructor
public class CandidateCertificationResource {
    public static final String PATH = "api/candidate-certifications";
    @Autowired
    CandidateCertificationService candidateCertificationService;

    @GetMapping
    public ResponseEntity<List<CandidateCertificationDto>> getAll() {

        return ResponseEntity.ok()
                .body(candidateCertificationService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CandidateCertificationDto> getById(@PathVariable("id") Integer id) throws EntityNotFoundException {

        return ResponseEntity.created(URI.create(PATH + "/" + id))
                .body(candidateCertificationService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CandidateCertificationDto> add(
            @RequestBody CandidateCertificationRequest inputData) throws EntityNotFoundException {
        CandidateCertification newCandidateCertification = candidateCertificationService.add(inputData);

        return ResponseEntity.created(URI.create(PATH + "/" + newCandidateCertification.getId()))
                .body(CandidateCertificationMapper.INSTANCE.toDto(newCandidateCertification));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CandidateCertificationDto> update(
            @PathVariable("id") Integer id, @RequestBody CandidateCertificationRequest updateDetail)
            throws EntityNotFoundException {

        return ResponseEntity.created(URI.create(PATH + "/" + id))
                .body(candidateCertificationService.update(updateDetail, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id)
            throws EntityNotFoundException {

        candidateCertificationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
