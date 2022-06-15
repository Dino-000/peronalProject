package com.axonactive.personalproject.service.serviceImpl;

import com.axonactive.personalproject.repository.UserAccountRepository;
import com.axonactive.personalproject.service.UserAccountService;
import com.axonactive.personalproject.service.security.UserAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {
    @Autowired
    UserAccountRepository userAccountRepository;


    @Override
    public List<UserAccount> getAll() {
        return userAccountRepository.findAll();
    }

    @Override
    public Optional<UserAccount> findByUserName(String userName) {
        return userAccountRepository.findById(userName);
    }

    @Override
    public UserAccount add(UserAccount userAccount) {
        return userAccountRepository.save(userAccount);
    }

    @Override
    public void deleteByUsername(String userName) {
        userAccountRepository.deleteById(userName);
    }
}
