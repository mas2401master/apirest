package com.apirest.repository;

import com.apirest.entity.Hits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HitsRepository extends JpaRepository<Hits,Long>, JpaSpecificationExecutor<Hits> {
    Optional<Hits> findById(Long id);
    boolean existsById(Long id);
}
