package com.axonactive.personalproject.controller;

import com.axonactive.personalproject.controller.request.CandidateCertificationRequest;
import com.axonactive.personalproject.entity.CandidateCertification;
import com.axonactive.personalproject.exception.ResourceNotFoundException;
import com.axonactive.personalproject.service.CandidateCertificationService;
import com.axonactive.personalproject.service.CandidateService;
import com.axonactive.personalproject.service.CertificationService;
import com.axonactive.personalproject.service.dto.CandidateCertificationDto;
import com.axonactive.personalproject.service.mapper.CandidateCertificationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
@RestController
@RequestMapping(CandidateCertificationResource.PATH)
@RequiredArgsConstructor
public class CandidateCertificationResource {
    public static final String PATH="api/CandidateCertifications";
    @Autowired
    CandidateCertificationService candidateCertificationService;
    @Autowired
    CandidateService candidateService;
    @Autowired
    CertificationService certificationService;

    @GetMapping
    public ResponseEntity<List<CandidateCertificationDto>> getAll() {
        return ResponseEntity.ok().body(CandidateCertificationMapper.INSTANCE.toDtos(candidateCertificationService.findAll()));
    }

    @PostMapping
    public ResponseEntity<CandidateCertificationDto> add(
            @RequestBody CandidateCertificationRequest inputData) {

        CandidateCertification newCandidateCertification = candidateCertificationService.saveCertificationList(new CandidateCertification(
                null,
                candidateService.findById(inputData.getCandidateId()).get(),
                certificationService.findById(inputData.getCertificationId()).get()
        ));

        return ResponseEntity.created(URI.create(PATH + "/" + newCandidateCertification.getId())).body(CandidateCertificationMapper.INSTANCE.toDto(newCandidateCertification));

    }

    @PutMapping("/{id}")
    public  ResponseEntity<CandidateCertificationDto> update(@PathVariable("id") Integer id,@RequestBody CandidateCertificationRequest updateDetail) throws ResourceNotFoundException {
        CandidateCertification updatingCandidateCertification = candidateCertificationService.findById(id).orElseThrow(()->new ResourceNotFoundException("Can't not find CandidateCertification with that id."));
        updatingCandidateCertification.setCandidate(candidateService.findById(updateDetail.getCandidateId()).get());
        updatingCandidateCertification.setCertification(certificationService.findById(updateDetail.getCertificationId()).get());
        CandidateCertification updatedCandidateCertification = candidateCertificationService.saveCertificationList(updatingCandidateCertification);
        return  ResponseEntity.created(URI.create(PATH+"/"+id)).body(CandidateCertificationMapper.INSTANCE.toDto(updatedCandidateCertification));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        CandidateCertification deletingCandidateCertification = candidateCertificationService.findById(id).orElseThrow(()->new ResourceNotFoundException("Can't not find Application Form with that id."));
        candidateCertificationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
