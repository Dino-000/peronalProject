package com.axonactive.personalproject.service.serviceImpl;

import com.axonactive.personalproject.controller.request.CandidateEducationRequest;
import com.axonactive.personalproject.entity.CandidateEducation;
import com.axonactive.personalproject.exception.ResourceNotFoundException;
import com.axonactive.personalproject.repository.CandidateEducationRepository;
import com.axonactive.personalproject.repository.CandidateRepository;
import com.axonactive.personalproject.repository.EducationRepository;
import com.axonactive.personalproject.service.CandidateEducationService;
import com.axonactive.personalproject.service.dto.CandidateEducationDto;
import com.axonactive.personalproject.service.mapper.CandidateEducationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CandidateEducationServiceImpl implements CandidateEducationService {
@Autowired
CandidateEducationRepository candidateEducationRepository;
@Autowired
    CandidateRepository candidateRepository;
@Autowired
    EducationRepository educationRepository;
    @Override
    public List<CandidateEducationDto> findAll() {
        return CandidateEducationMapper.INSTANCE.toDtos(candidateEducationRepository.findAll());
    }

    @Override
    public CandidateEducationDto findById(Integer id) throws ResourceNotFoundException {
        return CandidateEducationMapper.INSTANCE.toDto(candidateEducationRepository.findById(id).orElseThrow(
                () ->new ResourceNotFoundException("Can't not find Candidate Education with that id.")));
    }

    @Override
    public List<CandidateEducationDto> findByCandidateId(Integer id) {
        return CandidateEducationMapper.INSTANCE.toDtos(candidateEducationRepository.findByCandidateId(id));
    }

    @Override
    public CandidateEducation add(CandidateEducationRequest request) throws ResourceNotFoundException {
        return candidateEducationRepository.save(convertFromRequestToEntity(request));
    }

    @Override
    public CandidateEducationDto update(CandidateEducationRequest request, Integer id) throws ResourceNotFoundException {
        CandidateEducation updatingCandidateEducation =
                candidateEducationRepository
                        .findById(id)
                        .orElseThrow(
                                () ->
                                        new ResourceNotFoundException("Can't not find Candidate Education with that id."));
        updatingCandidateEducation.setCandidate(
                candidateRepository.findById(request.getCandidateId()).get());
        updatingCandidateEducation.setEducation(
                educationRepository.findById(request.getEducationId()).get());
        updatingCandidateEducation.setGraduationYear(request.getGraduationYear());

        return CandidateEducationMapper.INSTANCE.toDto(candidateEducationRepository.save(updatingCandidateEducation));
    }

    @Override
    public void deleteById(Integer id) throws ResourceNotFoundException {
CandidateEducationDto foundCandidateEducation = findById(id);
        candidateEducationRepository.deleteById(id);
    }

    @Override
    public CandidateEducation convertFromRequestToEntity(CandidateEducationRequest request) throws ResourceNotFoundException {
        return new CandidateEducation(
                null,
                candidateRepository.findById(request.getCandidateId()).orElseThrow(
                () ->new ResourceNotFoundException("Can't not find Candidate with that id.")),
                educationRepository.findById(request.getEducationId()).orElseThrow(
                () ->new ResourceNotFoundException("Can't not find = Education with that id.")),
        request.getGraduationYear());
    }


}
