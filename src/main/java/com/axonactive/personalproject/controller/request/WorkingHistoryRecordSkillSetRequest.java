package com.axonactive.personalproject.controller.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkingHistoryRecordSkillSetRequest {
    private Integer workingHistoryRecordId;
    private Integer skillSetId;
}
