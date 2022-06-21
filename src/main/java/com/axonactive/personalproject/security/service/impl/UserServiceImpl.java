package com.axonactive.personalproject.security.service.impl;

import com.axonactive.personalproject.security.repository.UserRepository;
import com.axonactive.personalproject.security.service.UserService;
import com.axonactive.personalproject.security.service.dto.UserDTO;
import com.axonactive.personalproject.security.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  @Autowired private final UserRepository userRepository;

  @Override
  public List<UserDTO> getUsers() {
    return UserMapper.INSTANCE.mapToDtos(userRepository.findAll());
  }
}
