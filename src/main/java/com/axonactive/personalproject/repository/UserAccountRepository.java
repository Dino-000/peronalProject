package com.axonactive.personalproject.repository;

import com.axonactive.personalproject.service.security.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, String> {}
