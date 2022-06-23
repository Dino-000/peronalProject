package com.axonactive.personalproject.service.serviceImpl;

import com.axonactive.personalproject.entity.RecruitmentChanel;
import com.axonactive.personalproject.exception.EntityNotFoundException;
import com.axonactive.personalproject.repository.RecruitmentChanelRepository;
import com.axonactive.personalproject.service.RecruitmentChanelService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecruitmentChanelServiceImpl implements RecruitmentChanelService {
  @Autowired RecruitmentChanelRepository recruitmentChanelRepository;

  @Override
  public List<RecruitmentChanel> findAll() {
    return recruitmentChanelRepository.findAll();
  }

  @Override
  public RecruitmentChanel findById(Integer id) {
    return recruitmentChanelRepository
        .findById(id)
        .orElseThrow(EntityNotFoundException::recruitmentChannelNotFound);
  }

  @Override
  public RecruitmentChanel add(RecruitmentChanel inputData) {
    return saveRecruitmentChanel(
        new RecruitmentChanel(
            null,
            inputData.getName(),
            inputData.getAdminAccount(),
            inputData.getAnnualMembershipFee(),
            inputData.getConversionRate(),
            inputData.getNumberOfSuccessfulPlacement()));
  }

  @Override
  public RecruitmentChanel update(RecruitmentChanel request, Integer id) {
    RecruitmentChanel updatingRecruitmentChannel = findById(id);
    updatingRecruitmentChannel.setAdminAccount(request.getAdminAccount());
    updatingRecruitmentChannel.setConversionRate(request.getConversionRate());
    updatingRecruitmentChannel.setName(request.getName());
    updatingRecruitmentChannel.setAnnualMembershipFee(request.getAnnualMembershipFee());
    updatingRecruitmentChannel.setNumberOfSuccessfulPlacement(
        request.getNumberOfSuccessfulPlacement());

    return recruitmentChanelRepository.save(updatingRecruitmentChannel);
  }

  @Override
  public void deleteById(Integer id) {
    findById(id);
    recruitmentChanelRepository.deleteById(id);
  }

  @Override
  public RecruitmentChanel saveRecruitmentChanel(RecruitmentChanel recruitmentChanel) {
    return recruitmentChanelRepository.save(recruitmentChanel);
  }
}
