package com.axonactive.personalproject.controller;
import com.axonactive.personalproject.entity.Certification;
import com.axonactive.personalproject.exception.ResourceNotFoundException;
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
    public static final String PATH ="api/Certifications";
    @Autowired
    CertificationService certificationService;


    @GetMapping
    public ResponseEntity<List<Certification>> getAll() {
        return ResponseEntity.ok().body(certificationService.findAll());
    }

    @PostMapping
    public ResponseEntity<Certification> add(
            @RequestBody Certification inputData) {
        Certification newCertification = certificationService.saveCertification(new Certification(null,
                inputData.getIssuerName(),
                inputData.getNameOfCertification(),
                inputData.getType()

        ));

        return ResponseEntity.created(URI.create(PATH + "/" + newCertification.getId())).body(newCertification);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<Certification> update(@PathVariable("id") Integer id, @RequestBody Certification inputData) throws ResourceNotFoundException {
        Certification updatingCertification = certificationService.findById(id).orElseThrow(()->new ResourceNotFoundException("Can't not find Application Form with that id."));
        updatingCertification.setIssuerName(inputData.getIssuerName());
        updatingCertification.setNameOfCertification(inputData.getNameOfCertification());
        updatingCertification.setType(inputData.getType());
        Certification updatedCertification = certificationService.saveCertification(updatingCertification);
        return  ResponseEntity.created(URI.create(PATH+"/"+id)).body(updatedCertification);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        Certification deletingCertification = certificationService.findById(id).orElseThrow(()->new ResourceNotFoundException("Can't not find Application Form with that id."));
        certificationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

