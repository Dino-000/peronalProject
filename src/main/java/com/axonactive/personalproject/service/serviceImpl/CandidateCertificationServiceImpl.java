package com.axonactive.personalproject.service.serviceImpl;

import com.axonactive.personalproject.controller.request.CandidateCertificationRequest;
import com.axonactive.personalproject.entity.CandidateCertification;
import com.axonactive.personalproject.exception.ResourceNotFoundException;
import com.axonactive.personalproject.repository.CandidateCertificationRepository;
import com.axonactive.personalproject.repository.CandidateRepository;
import com.axonactive.personalproject.repository.CertificationRepository;
import com.axonactive.personalproject.service.CandidateCertificationService;
import com.axonactive.personalproject.service.dto.CandidateCertificationDto;
import com.axonactive.personalproject.service.mapper.CandidateCertificationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CandidateCertificationServiceImpl implements CandidateCertificationService {
    @Autowired
    CandidateCertificationRepository candidateCertificationRepository;
    @Autowired
    CandidateRepository candidateRepository;
    @Autowired
    CertificationRepository certificationRepository;

    @Override
    public List<CandidateCertificationDto> findAll() {
        return CandidateCertificationMapper.INSTANCE.toDtos(candidateCertificationRepository.findAll());
    }

    @Override
    public CandidateCertificationDto findById(Integer id) throws ResourceNotFoundException {
        return CandidateCertificationMapper.INSTANCE.toDto(candidateCertificationRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Can't not find CandidateCertification with that id.")));
    }

    @Override
    public List<CandidateCertificationDto> findByCandidateId(Integer id) {
        return CandidateCertificationMapper.INSTANCE.toDtos(candidateCertificationRepository.findByCandidateId(id));
    }

    @Override
    public CandidateCertification add(CandidateCertificationRequest request) throws ResourceNotFoundException {
        return candidateCertificationRepository.save(convertRequestToEntity(request));
    }

    @Override
    public void deleteById(Integer id) throws ResourceNotFoundException {
        CandidateCertificationDto deletingCandidateCertification =
                findById(id);
        candidateCertificationRepository.deleteById(id);
    }

    @Override
    public CandidateCertificationDto update(CandidateCertificationRequest request, Integer id) throws ResourceNotFoundException {
        CandidateCertification updatingCandidateCertification =
                candidateCertificationRepository
                        .findById(id)
                        .orElseThrow(
                                () ->
                                        new ResourceNotFoundException(
                                                "Can't not find CandidateCertification with that id."));
        updatingCandidateCertification.setCandidate(
                candidateRepository.findById(request.getCandidateId()).orElseThrow(
                        () -> new ResourceNotFoundException("Can't not find Candidate with that id.")));
        updatingCandidateCertification.setCertification(
                certificationRepository.findById(request.getCertificationId()).orElseThrow(
                        () -> new ResourceNotFoundException("Can't not find Certification with that id.")));

        updatingCandidateCertification.setExpiredDate(request.getExpiredDate());
        updatingCandidateCertification.setIssuedDate(request.getIssuedDate());
        return CandidateCertificationMapper.INSTANCE.toDto(candidateCertificationRepository.save(updatingCandidateCertification));
    }


    @Override
    public CandidateCertification convertRequestToEntity(CandidateCertificationRequest request) throws ResourceNotFoundException {
        return new CandidateCertification(
                null,
                candidateRepository.findById(request.getCandidateId()).orElseThrow(
                        () -> new ResourceNotFoundException("Can't not find Candidate with that id.")),
                certificationRepository.findById(request.getCertificationId()).orElseThrow(
                        () -> new ResourceNotFoundException("Can't not find Certification with that id.")),
                request.getIssuedDate(),
                request.getExpiredDate());
    }


}
