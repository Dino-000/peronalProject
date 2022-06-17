package com.axonactive.personalproject.service.serviceImpl;

import com.axonactive.personalproject.entity.Education;
import com.axonactive.personalproject.exception.ResourceNotFoundException;
import com.axonactive.personalproject.repository.EducationRepository;
import com.axonactive.personalproject.service.EducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EducationServiceImpl implements EducationService {
  @Autowired EducationRepository educationRepository;

  @Override
  public List<Education> findAll() {
    return educationRepository.findAll();
  }

  @Override
  public Education findById(Integer id) throws ResourceNotFoundException {
    return educationRepository
        .findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Can't not find Education with that id."));
  }

  @Override
  public void deleteById(Integer id) throws ResourceNotFoundException {
    findById(id);
    educationRepository.deleteById(id);
  }

  @Override
  public Education saveEducation(Education inputData) {
    return educationRepository.save(
        new Education(
            null,
            inputData.getSchoolName(),
            inputData.getDegree(),
            inputData.getMajor(),
            inputData.getPrestigeRate()));
  }

  @Override
  public List<Education> findByCandidateId(Integer id) {
    return educationRepository.findByCandidateId(id);
  }

  @Override
  public Education update(Education inputData, Integer id) throws ResourceNotFoundException {
    Education updatingEducation =
        educationRepository
            .findById(id)
            .orElseThrow(
                () -> new ResourceNotFoundException("Can't not find Education with that id."));
    updatingEducation.setSchoolName(inputData.getSchoolName());
    updatingEducation.setDegree(inputData.getDegree());
    updatingEducation.setMajor(inputData.getMajor());
    updatingEducation.setPrestigeRate(inputData.getPrestigeRate());
    return educationRepository.save(updatingEducation);
  }
}
