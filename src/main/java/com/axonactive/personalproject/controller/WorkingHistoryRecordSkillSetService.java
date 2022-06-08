package com.axonactive.personalproject.controller;

import com.axonactive.personalproject.entity.WorkingHistoryRecordSkillSet;

import java.util.List;
import java.util.Optional;

public interface WorkingHistoryRecordSkillSetService {
    List<WorkingHistoryRecordSkillSet> findAll();
    Optional<WorkingHistoryRecordSkillSet> findById(Integer id);

    void deleteById(Integer id);

    WorkingHistoryRecordSkillSet saveWorkingHistoryRecordSkillSet(WorkingHistoryRecordSkillSet workingHistoryRecordSkillSet);
}
