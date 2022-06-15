package com.axonactive.personalproject.service;

import com.axonactive.personalproject.service.security.UserAccount;

import java.util.List;
import java.util.Optional;

public interface UserAccountService {
    List<UserAccount> getAll();
    Optional<UserAccount> findByUserName(String userName);
    UserAccount add(UserAccount userAccount);
    void deleteByUsername(String userName);

}
