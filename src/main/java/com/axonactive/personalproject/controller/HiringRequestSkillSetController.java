package com.axonactive.personalproject.controller;

import com.axonactive.personalproject.controller.request.HiringRequestSkillSetRequest;
import com.axonactive.personalproject.entity.HiringRequestSkillSet;
import com.axonactive.personalproject.entity.SkillSet;
import com.axonactive.personalproject.exception.ResourceNotFoundException;
import com.axonactive.personalproject.service.HiringRequestService;
import com.axonactive.personalproject.service.HiringRequestSkillSetService;
import com.axonactive.personalproject.service.SkillSetService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
@RestController
@RequestMapping(path = HiringRequestSkillSetController.PATH)
@RequiredArgsConstructor
public class HiringRequestSkillSetController {
    public static final String PATH ="api/HiringRequestSkillSets";
    @Autowired
    HiringRequestSkillSetService hiringRequestSkillSetService;
    @Autowired
    HiringRequestService hiringRequestService;
    @Autowired
    SkillSetService skillSetService;

    @GetMapping
    public ResponseEntity<List<HiringRequestSkillSet>> getAll() {
        return ResponseEntity.ok().body(hiringRequestSkillSetService.findAll());
    }

    @PostMapping
    public ResponseEntity<HiringRequestSkillSet> add(
            @RequestBody HiringRequestSkillSetRequest inputData) {
        HiringRequestSkillSet newHiringRequestSkillSet = hiringRequestSkillSetService.saveHiringRequestSkillSet(new HiringRequestSkillSet(null,
                hiringRequestService.findById(inputData.getHiringRequestId()).get(),
                skillSetService.findById(inputData.getSkillSetId()).get()
        ));
        return ResponseEntity.created(URI.create(PATH + "/" + newHiringRequestSkillSet.getId())).body(newHiringRequestSkillSet);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<HiringRequestSkillSet> update(@PathVariable("id") Integer id, @RequestBody HiringRequestSkillSetRequest inputData) throws ResourceNotFoundException {
        HiringRequestSkillSet updatingHiringRequestSkillSet = hiringRequestSkillSetService.findById(id).orElseThrow(()->new ResourceNotFoundException("Can't not find Application Form with that id."));
        updatingHiringRequestSkillSet.setHiringRequest(hiringRequestService.findById(inputData.getHiringRequestId()).get());
        updatingHiringRequestSkillSet.setSkillSet(skillSetService.findById(inputData.getSkillSetId()).get());
        HiringRequestSkillSet updatedHiringRequestSkillSet = hiringRequestSkillSetService.saveHiringRequestSkillSet(updatingHiringRequestSkillSet);
        return  ResponseEntity.created(URI.create(PATH+"/"+id)).body(updatedHiringRequestSkillSet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        HiringRequestSkillSet deletingHiringRequestSkillSet = hiringRequestSkillSetService.findById(id).orElseThrow(()->new ResourceNotFoundException("Can't not find Application Form with that id."));
        hiringRequestSkillSetService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

