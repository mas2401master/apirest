package com.apirest.service;

import com.apirest.dto.HitResponse;
import com.apirest.entity.Hits;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface HitsService {
    Hits createHitsDataSource(Hits hits);
    Hits mapEntity(HitResponse hitResponse);
    Page<Hits> findAll(Pageable page);
    Hits findById(Long id);
    void deleteHits(Long id);

}
