package com.axonactive.personalproject.service;

import com.axonactive.personalproject.service.security.UserAccount;

import java.util.List;

public interface UserAccountService {
  List<UserAccount> getAll();

  UserAccount findByUserName(String userName);

  UserAccount add(UserAccount userAccount);

  void deleteByUsername(String userName);

  UserAccount update(String userName, String password);
}
