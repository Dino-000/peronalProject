package com.axonactive.personalproject.controller;

import com.axonactive.personalproject.controller.request.CandidateSkillSetRequest;
import com.axonactive.personalproject.entity.CandidateSkillSet;
import com.axonactive.personalproject.exception.ResourceNotFoundException;
import com.axonactive.personalproject.service.CandidateService;
import com.axonactive.personalproject.service.CandidateSkillSetService;
import com.axonactive.personalproject.service.SkillSetService;
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
    public static final String PATH ="api/CandidateSkillSets";
    @Autowired
    CandidateSkillSetService candidateSkillSetService;
    @Autowired
    SkillSetService skillSetService;
    @Autowired
    CandidateService candidateService;

    @GetMapping
    public ResponseEntity<List<CandidateSkillSetDto>> getAll() {
        return ResponseEntity.ok().body(CandidateSkillSetMapper.INSTANCE.toDtos(candidateSkillSetService.findAll()));
    }

    @PostMapping
    public ResponseEntity<CandidateSkillSetDto> add(
            @RequestBody CandidateSkillSetRequest inputData) {
        CandidateSkillSet newCandidateSkillSet = candidateSkillSetService.saveSkillSetList(new CandidateSkillSet(null,
                candidateService.findById(inputData.getCandidateId()).get(),
                skillSetService.findById(inputData.getSkillSetId()).get()
        ));

        return ResponseEntity.created(URI.create(PATH + "/" + newCandidateSkillSet.getId())).body(CandidateSkillSetMapper.INSTANCE.toDto(newCandidateSkillSet));
    }

    @PutMapping("/{id}")
    public  ResponseEntity<CandidateSkillSetDto> update(@PathVariable("id") Integer id, @RequestBody CandidateSkillSetRequest inputData) throws ResourceNotFoundException {
        CandidateSkillSet updatingCandidateSkillSet = candidateSkillSetService.findById(id).orElseThrow(()->new ResourceNotFoundException("Can't not find Application Form with that id."));
        updatingCandidateSkillSet.setCandidate(candidateService.findById(inputData.getCandidateId()).get());
        updatingCandidateSkillSet.setSkillSet(skillSetService.findById(inputData.getSkillSetId()).get());
        CandidateSkillSet updatedCandidateSkillSet = candidateSkillSetService.saveSkillSetList(updatingCandidateSkillSet);
        return  ResponseEntity.created(URI.create(PATH+"/"+id)).body(CandidateSkillSetMapper.INSTANCE.toDto(updatedCandidateSkillSet));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        CandidateSkillSet deletingCandidateSkillSet = candidateSkillSetService.findById(id).orElseThrow(()->new ResourceNotFoundException("Can't not find Application Form with that id."));
        candidateSkillSetService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

