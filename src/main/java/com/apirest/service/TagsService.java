package com.apirest.service;

import com.apirest.entity.Hits;
import com.apirest.entity.Tags;

import java.util.ArrayList;

public interface TagsService {
    Tags createTagsDataSource(String tagsResponse);
}
