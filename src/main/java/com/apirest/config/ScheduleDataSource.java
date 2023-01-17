package com.apirest.config;

import com.apirest.dto.DataResult;
import com.apirest.entity.Highlight;
import com.apirest.entity.Hits;
import com.apirest.entity.Tags;
import com.apirest.service.ApiRestDataService;
import com.apirest.service.HighlightService;
import com.apirest.service.HitsService;
import com.apirest.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@EnableScheduling
public class ScheduleDataSource {
    private final long SECONS = 1000;
    private final long MINUTE = SECONS * 60;
    private final long HOUR = MINUTE * 60;

    @Autowired
    private ApiRestDataService dataService;

    @Autowired
    private HitsService hitsService;
    @Autowired
    private TagsService tagsService;

    @Autowired
    private HighlightService highlightService;

    @Scheduled(fixedDelay = HOUR)
    public void runScheduleDataSource() {
        DataResult dataResult = dataService.getDataSource();
        dataResult.getHits().forEach(hitsResponse -> {
            Hits hits = hitsService.mapEntity(hitsResponse);
            //insert tags
            List<Tags> _tags = new ArrayList<>();
            hitsResponse.get_tags().forEach(tagsResponse ->{
                Tags tags = new Tags();
                tags = tagsService.createTagsDataSource(tagsResponse);
                _tags.add(tags);
            });
            //insert hits
            hits.setTags(_tags);
            Hits hitsNew =  hitsService.createHitsDataSource(hits);
            //insert highlight
            highlightService.createHighlightDataSource(hitsResponse.get_highlightResult(), hitsNew);
        });
    }
}
