package com.axonactive.personalproject.service.serviceImpl;

import com.axonactive.personalproject.entity.ApplicationForm;
import com.axonactive.personalproject.repository.ApplicationFormRepository;
import com.axonactive.personalproject.service.ApplicationFormService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApplicationFormServiceImpl implements ApplicationFormService {
@Autowired
    ApplicationFormRepository applicationFormRepository;


    @Override
    public List<ApplicationForm> findAll() {
        return applicationFormRepository.findAll();
    }

    @Override
    public Optional<ApplicationForm> findById(Integer id) {
        return applicationFormRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        applicationFormRepository.deleteById(id);
    }

    @Override
    public ApplicationForm saveApplicationForm(ApplicationForm applicationForm) {
        return applicationFormRepository.save(applicationForm);
    }

    @Override
    public List<ApplicationForm> findBySubmittedDateBetween(LocalDate fromDate, LocalDate untilDate) {
        return applicationFormRepository.findBySubmittedDateBetween(fromDate,untilDate);
    }
//no controller
    @Override
    public List<ApplicationForm> findFirstByCandidateIdOrderBySubmittedDateDesc(Integer Id) {
        return applicationFormRepository.findFirstByCandidateIdOrderBySubmittedDateDesc(Id);
    }

    @Override
    public List<ApplicationForm> findByHiringRequestHiringManagerId(Integer Id) {
        return applicationFormRepository.findByHiringRequestHiringManagerId(Id);
    }
}