package com.axonactive.personalproject.controller;

import com.axonactive.personalproject.entity.RecruitmentChanel;
import com.axonactive.personalproject.exception.ResourceNotFoundException;
import com.axonactive.personalproject.service.RecruitmentChanelService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
@RestController
@RequestMapping(path = RecruitmentChanelResource.PATH)
@RequiredArgsConstructor
public class RecruitmentChanelResource {
    public static final String PATH ="api/RecruitmentChannels";
    @Autowired
    RecruitmentChanelService recruitmentChanelService;


    @GetMapping
    public ResponseEntity<List<RecruitmentChanel>> getAll() {
        return ResponseEntity.ok().body(recruitmentChanelService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecruitmentChanel> getById (@PathVariable("id") Integer id) throws ResourceNotFoundException {
        RecruitmentChanel recruitmentChanel = recruitmentChanelService.findById(id).orElseThrow(()->new ResourceNotFoundException("Can't not find RecruitmentChanel with that id."));
        return ResponseEntity.created(URI.create(PATH+"/"+recruitmentChanel.getId())).body(recruitmentChanel);
    }

    @PostMapping
    public ResponseEntity<RecruitmentChanel> add(
            @RequestBody RecruitmentChanel inputData) {
        RecruitmentChanel newRecruitmentChanel = recruitmentChanelService.saveRecruitmentChanel(new RecruitmentChanel(null,
                inputData.getName(),
                inputData.getAdminAccount(),
                inputData.getAnnualMembershipFee(),
                inputData.getConversionRate(),
                inputData.getNumberOfSuccessfulPlacement()
        ));

        return ResponseEntity.created(URI.create(PATH + "/" + newRecruitmentChanel.getId())).body(newRecruitmentChanel);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<RecruitmentChanel> update(@PathVariable("id") Integer id, @RequestBody RecruitmentChanel inputData) throws ResourceNotFoundException {
        RecruitmentChanel updatingRecruitmentChanel = recruitmentChanelService.findById(id).orElseThrow(()->new ResourceNotFoundException("Can't not find RecruitmentChanel with that id."));
        updatingRecruitmentChanel.setName(inputData.getName());
        updatingRecruitmentChanel.setAdminAccount(inputData.getAdminAccount()   );
        updatingRecruitmentChanel.setAnnualMembershipFee(inputData.getAnnualMembershipFee());
        updatingRecruitmentChanel.setConversionRate(inputData.getConversionRate());
        updatingRecruitmentChanel.setNumberOfSuccessfulPlacement(inputData.getNumberOfSuccessfulPlacement());

        RecruitmentChanel updatedRecruitmentChanel = recruitmentChanelService.saveRecruitmentChanel(updatingRecruitmentChanel);
        return  ResponseEntity.created(URI.create(PATH+"/"+id)).body(updatedRecruitmentChanel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        RecruitmentChanel deletingRecruitmentChanel = recruitmentChanelService.findById(id).orElseThrow(()->new ResourceNotFoundException("Can't not find RecruitmentChanel with that id."));
        recruitmentChanelService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

