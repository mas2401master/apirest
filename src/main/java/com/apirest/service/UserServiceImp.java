package com.apirest.service;

import com.apirest.dto.UserDTO;
import com.apirest.entity.Rol;
import com.apirest.entity.UserApp;
import com.apirest.enums.RolName;
import com.apirest.exception.ApiException;
import com.apirest.repository.RolRepository;
import com.apirest.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImp implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserApp findByUsername(String username) {
        return userRepository.findByUsername(username).get();
    }

    @Override
    public UserApp findByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        if(existsByUsername(userDTO.getUsername())){
            throw new ApiException(HttpStatus.BAD_REQUEST,"Username already exists");
        }
        if(existsByEmail(userDTO.getEmail())){
            throw new ApiException(HttpStatus.BAD_REQUEST,"Email already exists");
        }
        Set<Rol> rols = new HashSet<>();
        rols.add(rolRepository.findByName(RolName.ROLE_USER).get());

        UserApp saveUser = mapEntityUser(userDTO);
        saveUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        saveUser.setRols(rols);
        UserApp saveNew =userRepository.save(saveUser);
        return mapDtoUser(saveNew);
    }

    public UserApp mapEntityUser(UserDTO registerDTO){
        return modelMapper.map(registerDTO, UserApp.class);
    }

    public UserDTO mapDtoUser(UserApp user){
        return modelMapper.map(user, UserDTO.class);
    }
}
