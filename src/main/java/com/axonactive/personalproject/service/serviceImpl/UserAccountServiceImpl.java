package com.axonactive.personalproject.service.serviceImpl;

import com.axonactive.personalproject.exception.EntityNotFoundException;
import com.axonactive.personalproject.repository.UserAccountRepository;
import com.axonactive.personalproject.service.UserAccountService;
import com.axonactive.personalproject.service.security.UserAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {
  @Autowired UserAccountRepository userAccountRepository;

  @Override
  public List<UserAccount> getAll() {
    return userAccountRepository.findAll();
  }

  @Override
  public UserAccount findByUserName(String userName) {
    return userAccountRepository
        .findById(userName)
        .orElseThrow(EntityNotFoundException::userAccountNotFound);
  }

  @Override
  public UserAccount add(UserAccount userAccount) {
    return userAccountRepository.save(userAccount);
  }

  @Override
  public void deleteByUsername(String userName) {
    userAccountRepository.deleteById(userName);
  }

  @Override
  public UserAccount update(String userName, String password) {
    UserAccount updatingUserAccount = findByUserName(userName);
    updatingUserAccount.setPassWord(password);

    return userAccountRepository.save(updatingUserAccount);
  }
}
