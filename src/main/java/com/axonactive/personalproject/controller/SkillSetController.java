package com.axonactive.personalproject.controller;

import com.axonactive.personalproject.entity.SkillSet;
import com.axonactive.personalproject.exception.ResourceNotFoundException;
import com.axonactive.personalproject.service.SkillSetService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(SkillSetController.PATH)
@RequiredArgsConstructor
public class SkillSetController {
    public static final String PATH="api/SkillSets";
    @Autowired
    SkillSetService skillSetService;

    @GetMapping
    public ResponseEntity<List<SkillSet>> getAll() {
        return ResponseEntity.ok().body(skillSetService.findAll());
    }

    @PostMapping
    public ResponseEntity<SkillSet> add(
            @RequestBody SkillSet inputData) {
        SkillSet newSkillSet = skillSetService.saveSkillSet(inputData);

        return ResponseEntity.created(URI.create(PATH + "/" + newSkillSet.getId())).body(newSkillSet);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<SkillSet> update(@PathVariable("id") Integer id,@RequestBody SkillSet updateDetail) throws ResourceNotFoundException {
        SkillSet updatingSkillSet = skillSetService.findById(id).orElseThrow(()->new ResourceNotFoundException("Can't not find Application Form with that id."));
        updatingSkillSet.setIndustryCategory(updateDetail.getIndustryCategory());
        updatingSkillSet.setName(updateDetail.getName());
        updatingSkillSet.setType(updateDetail.getType());
        updatingSkillSet.setLevel(updateDetail.getLevel());
        return  ResponseEntity.created(URI.create(PATH+"/"+id)).body(updatingSkillSet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        SkillSet deletingSkillSet = skillSetService.findById(id).orElseThrow(()->new ResourceNotFoundException("Can't not find Application Form with that id."));
        skillSetService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
