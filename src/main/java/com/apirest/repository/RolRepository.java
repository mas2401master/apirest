package com.apirest.repository;

import com.apirest.entity.Rol;
import com.apirest.enums.RolName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findByName(RolName name);
    Boolean existsByName(RolName username);
}
