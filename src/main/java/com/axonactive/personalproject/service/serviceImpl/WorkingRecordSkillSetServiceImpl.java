package com.axonactive.personalproject.service.serviceImpl;

import com.axonactive.personalproject.entity.WorkingHistoryRecordSkillSet;
import com.axonactive.personalproject.repository.WorkingHistoryRecordSkillSetRepository;
import com.axonactive.personalproject.service.WorkingHistoryRecordSkillSetService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class WorkingRecordSkillSetServiceImpl implements WorkingHistoryRecordSkillSetService {
    @Autowired
    WorkingHistoryRecordSkillSetRepository workingHistoryRecordSkillSetRepository;
    @Override
    public List<WorkingHistoryRecordSkillSet> findAll() {
        return workingHistoryRecordSkillSetRepository.findAll();
    }

    @Override
    public Optional<WorkingHistoryRecordSkillSet> findById(Integer id) {
        return workingHistoryRecordSkillSetRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
workingHistoryRecordSkillSetRepository.deleteById(id);
    }

    @Override
    public WorkingHistoryRecordSkillSet saveWorkingHistoryRecordSkillSet(WorkingHistoryRecordSkillSet workingHistoryRecordSkillSet) {
        return workingHistoryRecordSkillSetRepository.save(workingHistoryRecordSkillSet);
    }

    @Override
    public List<WorkingHistoryRecordSkillSet> findByWorkingHistoryRecordCandidateId(Integer id) {
        return workingHistoryRecordSkillSetRepository.findByWorkingHistoryRecordCandidateId(id);
    }


}
