package com.axonactive.personalproject.controller;

import com.axonactive.personalproject.controller.request.HiringRequestSkillSetRequest;
import com.axonactive.personalproject.entity.HiringRequestSkillSet;
import com.axonactive.personalproject.exception.ResourceNotFoundException;
import com.axonactive.personalproject.service.HiringRequestService;
import com.axonactive.personalproject.service.HiringRequestSkillSetService;
import com.axonactive.personalproject.service.SkillSetService;
import com.axonactive.personalproject.service.dto.HiringRequestSkillSetDto;
import com.axonactive.personalproject.service.mapper.HiringRequestSkillSetMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
@RestController
@RequestMapping(path = HiringRequestSkillSetResource.PATH)
@RequiredArgsConstructor
public class HiringRequestSkillSetResource {
    public static final String PATH ="api/HiringRequestSkillSets";
    @Autowired
    HiringRequestSkillSetService hiringRequestSkillSetService;
    @Autowired
    HiringRequestService hiringRequestService;
    @Autowired
    SkillSetService skillSetService;

    @GetMapping
    public ResponseEntity<List<HiringRequestSkillSetDto>> getAll() {
        return ResponseEntity.ok().body(HiringRequestSkillSetMapper.INSTANCE.toDtos(hiringRequestSkillSetService.findAll()));
    }

    @PostMapping
    public ResponseEntity<HiringRequestSkillSetDto> add(
            @RequestBody HiringRequestSkillSetRequest inputData) {
        HiringRequestSkillSet newHiringRequestSkillSet = hiringRequestSkillSetService.saveHiringRequestSkillSet(new HiringRequestSkillSet(null,
                hiringRequestService.findById(inputData.getHiringRequestId()).get(),
                skillSetService.findById(inputData.getSkillSetId()).get()
        ));
        return ResponseEntity.created(URI.create(PATH + "/" + newHiringRequestSkillSet.getId())).body(HiringRequestSkillSetMapper.INSTANCE.toDto(newHiringRequestSkillSet));
    }

    @PutMapping("/{id}")
    public  ResponseEntity<HiringRequestSkillSetDto> update(@PathVariable("id") Integer id, @RequestBody HiringRequestSkillSetRequest inputData) throws ResourceNotFoundException {
        HiringRequestSkillSet updatingHiringRequestSkillSet = hiringRequestSkillSetService.findById(id).orElseThrow(()->new ResourceNotFoundException("Can't not find Application Form with that id."));
        updatingHiringRequestSkillSet.setHiringRequest(hiringRequestService.findById(inputData.getHiringRequestId()).get());
        updatingHiringRequestSkillSet.setSkillSet(skillSetService.findById(inputData.getSkillSetId()).get());
        HiringRequestSkillSet updatedHiringRequestSkillSet = hiringRequestSkillSetService.saveHiringRequestSkillSet(updatingHiringRequestSkillSet);
        return  ResponseEntity.created(URI.create(PATH+"/"+id)).body(HiringRequestSkillSetMapper.INSTANCE.toDto(updatedHiringRequestSkillSet));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        HiringRequestSkillSet deletingHiringRequestSkillSet = hiringRequestSkillSetService.findById(id).orElseThrow(()->new ResourceNotFoundException("Can't not find Application Form with that id."));
        hiringRequestSkillSetService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

