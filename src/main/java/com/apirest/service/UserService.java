package com.apirest.service;

import com.apirest.dto.UserDTO;
import com.apirest.entity.UserApp;

public interface UserService {
    UserApp findByUsername(String username);
    UserApp findByEmail(String email);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    UserDTO createUser(UserDTO registerDTO);
}
