package com.axonactive.personalproject.controller;

import com.axonactive.personalproject.controller.request.CandidateSkillSetRequest;
import com.axonactive.personalproject.entity.CandidateSkillSet;
import com.axonactive.personalproject.exception.ResourceNotFoundException;
import com.axonactive.personalproject.service.CandidateSkillSetService;
import com.axonactive.personalproject.service.dto.CandidateSkillSetDto;
import com.axonactive.personalproject.service.mapper.CandidateSkillSetMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = CandidateSkillSetResource.PATH)
@RequiredArgsConstructor
public class CandidateSkillSetResource {
    public static final String PATH ="api/candidate-skillsets";
    @Autowired
    CandidateSkillSetService candidateSkillSetService;


    @GetMapping
    public ResponseEntity<List<CandidateSkillSetDto>> getAll() {
        return ResponseEntity.ok().body(candidateSkillSetService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CandidateSkillSetDto> getById (@PathVariable("id") Integer id) throws ResourceNotFoundException {
        return ResponseEntity.created(URI.create(PATH+"/"+id)).body(candidateSkillSetService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CandidateSkillSetDto> add(
            @RequestBody CandidateSkillSetRequest inputData) throws ResourceNotFoundException {
        CandidateSkillSet newCandidateSkillSet = candidateSkillSetService.add(inputData);

        return ResponseEntity.created(URI.create(PATH + "/" + newCandidateSkillSet.getId())).body(CandidateSkillSetMapper.INSTANCE.toDto(newCandidateSkillSet));
    }

    @PutMapping("/{id}")
    public  ResponseEntity<CandidateSkillSetDto> update(@PathVariable("id") Integer id, @RequestBody CandidateSkillSetRequest inputData) throws ResourceNotFoundException {
        return  ResponseEntity.created(URI.create(PATH+"/"+id)).body(candidateSkillSetService.update(inputData,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        candidateSkillSetService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

