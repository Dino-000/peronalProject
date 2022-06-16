package com.axonactive.personalproject.security.service;

import com.axonactive.personalproject.security.service.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getUsers();
}
