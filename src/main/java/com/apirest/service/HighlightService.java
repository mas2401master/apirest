package com.apirest.service;

import com.apirest.dto.HighlightResultResponse;
import com.apirest.entity.Highlight;
import com.apirest.entity.Hits;

import java.util.List;

public interface HighlightService {
    void createHighlightDataSource(HighlightResultResponse highlightResultResponse, Hits hitsNew);
}
