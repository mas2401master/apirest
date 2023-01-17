package com.apirest.repository;

import com.apirest.entity.MatchedWords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MatchedWordsRepository extends JpaRepository<MatchedWords, Long> {
    Optional<MatchedWords> findByDescription(String description);
}
