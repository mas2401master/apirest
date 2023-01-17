package com.apirest.service;

import com.apirest.entity.Rol;
import com.apirest.enums.RolName;

public interface RolService {
    void createRol(Rol rol);
    Rol findByName(RolName name);
    Boolean existsByName(RolName username);
}
