package com.axonactive.personalproject.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateCertificationDto {
    private String candidateName;
    private String certificationName;
    private String issuerName;
    private String type;
    private LocalDate issuedDate;
    private LocalDate expiredDate;

}
