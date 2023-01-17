package com.apirest.repository;

import com.apirest.entity.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagsRepository  extends JpaRepository<Tags, Long> {
    public Optional<Tags> findByDescription(String description);
}
