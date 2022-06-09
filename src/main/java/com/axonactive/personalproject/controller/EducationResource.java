package com.axonactive.personalproject.controller;

import com.axonactive.personalproject.entity.Education;
import com.axonactive.personalproject.exception.ResourceNotFoundException;
import com.axonactive.personalproject.service.EducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
@RestController
@RequestMapping(path = EducationResource.PATH)
@RequiredArgsConstructor
public class EducationResource {
    public static final String PATH ="api/Educations";
    @Autowired
    EducationService educationService;


    @GetMapping
    public ResponseEntity<List<Education>> getAll() {
        return ResponseEntity.ok().body(educationService.findAll());
    }

    @PostMapping
    public ResponseEntity<Education> add(
            @RequestBody Education inputData) {
        Education newEducation = educationService.saveEducation(new Education(null,
                inputData.getSchoolName(),
                inputData.getDegree(),
                inputData.getMajor(),
                inputData.getGraduationYear(),
                inputData.getPrestigeRate()
        ));

        return ResponseEntity.created(URI.create(PATH + "/" + newEducation.getId())).body(newEducation);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<Education> update(@PathVariable("id") Integer id, @RequestBody Education inputData) throws ResourceNotFoundException {
        Education updatingEducation = educationService.findById(id).orElseThrow(()->new ResourceNotFoundException("Can't not find Application Form with that id."));
        updatingEducation.setSchoolName(inputData.getSchoolName());
        updatingEducation.setDegree(inputData.getSchoolName());
        updatingEducation.setGraduationYear(inputData.getGraduationYear());
        updatingEducation.setMajor(inputData.getMajor());
        updatingEducation.setPrestigeRate(inputData.getPrestigeRate());

        Education updatedEducation = educationService.saveEducation(updatingEducation);
        return  ResponseEntity.created(URI.create(PATH+"/"+id)).body(updatedEducation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        Education deletingEducation = educationService.findById(id).orElseThrow(()->new ResourceNotFoundException("Can't not find Application Form with that id."));
        educationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

