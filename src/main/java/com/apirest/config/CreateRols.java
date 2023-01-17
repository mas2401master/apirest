package com.apirest.config;

import com.apirest.entity.Rol;
import com.apirest.enums.RolName;
import com.apirest.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CreateRols implements CommandLineRunner {
    @Autowired
    private RolService rolService;

    @Override
    public void run(String... args) throws Exception {
        if(!rolService.existsByName(RolName.ROLE_ADMIN)){
            Rol rolAdmin = new Rol();
            rolAdmin.setName(RolName.ROLE_ADMIN);
            rolService.createRol(rolAdmin);
        }
        if(!rolService.existsByName(RolName.ROLE_USER)){
            Rol rolUser = new Rol();
            rolUser.setName(RolName.ROLE_USER);
            rolService.createRol(rolUser);
        }
    }
}
