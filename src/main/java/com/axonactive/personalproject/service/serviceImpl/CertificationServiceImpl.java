package com.axonactive.personalproject.service.serviceImpl;

import com.axonactive.personalproject.entity.Certification;
import com.axonactive.personalproject.exception.ResourceNotFoundException;
import com.axonactive.personalproject.repository.CertificationRepository;
import com.axonactive.personalproject.service.CertificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CertificationServiceImpl implements CertificationService {
  @Autowired CertificationRepository certificationRepository;

  @Override
  public List<Certification> findAll() {
    return certificationRepository.findAll();
  }

  @Override
  public Certification findById(Integer id) throws ResourceNotFoundException {
    return certificationRepository
        .findById(id)
        .orElseThrow(
                ResourceNotFoundException::certificationNotFound);
  }

  @Override
  public void deleteById(Integer id) throws ResourceNotFoundException {
    findById(id);
    certificationRepository.deleteById(id);
  }

  @Override
  public Certification saveCertification(Certification certification) {
    return certificationRepository.save(certification);
  }

  @Override
  public List<Certification> findByCandidateId(Integer id) {
    return certificationRepository.findByCandidateId(id);
  }

  @Override
  public Certification update(Certification request, Integer id) throws ResourceNotFoundException {
    Certification updatingCertification =
        certificationRepository
            .findById(id)
            .orElseThrow(
                    ResourceNotFoundException::certificationNotFound);
    updatingCertification.setIssuerName(request.getIssuerName());
    updatingCertification.setNameOfCertification(request.getNameOfCertification());
    updatingCertification.setType(request.getType());
    return saveCertification(updatingCertification);
  }

  @Override
  public Certification add(Certification request) {
    return saveCertification(
        new Certification(
            null, request.getIssuerName(), request.getNameOfCertification(), request.getType()));
  }
}
