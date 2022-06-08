package com.axonactive.personalproject.controller;

import com.axonactive.personalproject.service.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(CandidateController.PATH)
public class CandidateController {
    public static final String PATH ="api/Candidates";
    @Autowired
    CandidateService candidateService;

}
