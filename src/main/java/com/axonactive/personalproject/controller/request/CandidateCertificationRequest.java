package com.axonactive.personalproject.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateCertificationRequest {
    private Integer candidateId;
    private Integer certificationId;
    private LocalDate issuedDate;
    private LocalDate expiredDate;
}
