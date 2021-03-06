package com.axonactive.personalproject.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HiringRequestSkillSetRequest {
  private Integer hiringRequestId;
  private Integer skillSetId;
}
