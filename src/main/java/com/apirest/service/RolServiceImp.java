package com.apirest.service;

import com.apirest.entity.Rol;
import com.apirest.enums.RolName;
import com.apirest.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolServiceImp implements RolService{
    @Autowired
    private RolRepository rolRepository;

    @Override
    public void createRol(Rol rol) {
        rolRepository.save(rol);
    }

    @Override
    public Rol findByName(RolName name) {
        return rolRepository.findByName(name).get();
    }

    @Override
    public Boolean existsByName(RolName name) {
        return rolRepository.existsByName(name);
    }
}
