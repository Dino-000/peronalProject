package com.axonactive.personalproject.controller;

import com.axonactive.personalproject.controller.request.CandidateEducationRequest;
import com.axonactive.personalproject.entity.CandidateEducation;
import com.axonactive.personalproject.exception.EntityNotFoundException;
import com.axonactive.personalproject.service.CandidateEducationService;
import com.axonactive.personalproject.service.dto.CandidateEducationDto;
import com.axonactive.personalproject.service.mapper.CandidateEducationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(CandidateEducationResource.PATH)
public class CandidateEducationResource {
    public static final String PATH = "api/candidate-educations";
    @Autowired
    CandidateEducationService candidateEducationService;


    @GetMapping
    public ResponseEntity<List<CandidateEducationDto>> getAll() {
        return ResponseEntity.ok()
                .body(candidateEducationService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CandidateEducationDto> getById(@PathVariable("id") Integer id) throws EntityNotFoundException {

        return ResponseEntity.created(URI.create(PATH + "/" + id)).body(candidateEducationService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CandidateEducationDto> add(
            @RequestBody CandidateEducationRequest inputData) throws EntityNotFoundException {
        CandidateEducation newCandidateEducation =
                candidateEducationService.add(inputData);

        return ResponseEntity.created(URI.create(PATH + "/" + newCandidateEducation.getId()))
                .body(CandidateEducationMapper.INSTANCE.toDto(newCandidateEducation));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CandidateEducationDto> update(
            @PathVariable("id") Integer id, @RequestBody CandidateEducationRequest inputData)
            throws EntityNotFoundException {


        return ResponseEntity.created(URI.create(PATH + "/" + id))
                .body(candidateEducationService.update(inputData, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id)
            throws EntityNotFoundException {

        candidateEducationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
