package com.axonactive.personalproject.repository;

import com.axonactive.personalproject.service.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String>
{
}
