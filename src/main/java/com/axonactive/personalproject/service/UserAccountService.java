package com.axonactive.personalproject.service;

import com.axonactive.personalproject.exception.EntityNotFoundException;
import com.axonactive.personalproject.service.security.UserAccount;

import java.util.List;

public interface UserAccountService {
    List<UserAccount> getAll();
    UserAccount findByUserName(String userName) throws EntityNotFoundException;
    UserAccount add(UserAccount userAccount);
    void deleteByUsername(String userName);
    UserAccount update (String userName, String password) throws EntityNotFoundException;


}
