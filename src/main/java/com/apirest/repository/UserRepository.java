package com.apirest.repository;

import com.apirest.entity.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserApp, Long> {
    Optional<UserApp> findByUsername(String username);
    public Optional<UserApp> findByEmail(String email);
    public Boolean existsByUsername(String username);
    public Boolean existsByEmail(String email);
}
