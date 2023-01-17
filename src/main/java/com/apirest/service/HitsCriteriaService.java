package com.apirest.service;

import com.apirest.dto.HitsFilterDTO;
import com.apirest.entity.Hits;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HitsCriteriaService {
    Page<Hits> findByCriteria(Pageable page, HitsFilterDTO filter);
}
