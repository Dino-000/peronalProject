package com.axonactive.personalproject.controller;

import com.axonactive.personalproject.entity.Candidate;
import com.axonactive.personalproject.exception.ResourceNotFoundException;
import com.axonactive.personalproject.service.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(CandidateResource.PATH)
public class CandidateResource {
    public static final String PATH ="api/Candidates";
    @Autowired
    CandidateService candidateService;

    @GetMapping
    public ResponseEntity<List<Candidate>> getAll() {
        return ResponseEntity.ok().body(candidateService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidate> getById (@PathVariable("id") Integer id) throws ResourceNotFoundException {
        Candidate candidate = candidateService.findById(id).orElseThrow(()->new ResourceNotFoundException("Can't not find Candidate with that id."));
        return ResponseEntity.created(URI.create(PATH+"/"+candidate.getId())).body(candidate);
    }

    @PostMapping
    public ResponseEntity<Candidate> add(
            @RequestBody Candidate inputData) {
        Candidate newCandidate = candidateService.saveCandidate(inputData);

        return ResponseEntity.created(URI.create(PATH + "/" + newCandidate.getId())).body(newCandidate);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<Candidate> update(@PathVariable("id") Integer id,@RequestBody Candidate updateDetail) throws ResourceNotFoundException {
        Candidate updatingCandidate = candidateService.findById(id).orElseThrow(()->new ResourceNotFoundException("Can't not find Candidate with that id."));
        updatingCandidate.setName(updateDetail.getName());
        updatingCandidate.setDateOfBirth(updateDetail.getDateOfBirth());
        updatingCandidate.setLocation(updateDetail.getLocation());
        updatingCandidate.setOccupation(updateDetail.getOccupation());
        updatingCandidate.setSeniority(updateDetail.getSeniority());
        updatingCandidate.setGpa(updateDetail.getGpa());
        Candidate updatedCandidate = candidateService.saveCandidate(updatingCandidate);
        return  ResponseEntity.created(URI.create(PATH+"/"+id)).body(updatedCandidate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        Candidate deletingCandidate = candidateService.findById(id).orElseThrow(()->new ResourceNotFoundException("Can't not find Candidate with that id."));
        candidateService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
