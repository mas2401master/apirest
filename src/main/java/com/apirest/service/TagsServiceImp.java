package com.apirest.service;

import com.apirest.entity.Hits;
import com.apirest.entity.Tags;
import com.apirest.repository.TagsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TagsServiceImp implements TagsService{
    @Autowired
    private TagsRepository tagsRepository;

    @Override
    public Tags createTagsDataSource(String tagsResponse) {
        Optional<Tags> tags = tagsRepository.findByDescription(tagsResponse);
        if (tags.isEmpty()){
            Tags tags1 = new Tags();
            tags1.setDescription(tagsResponse);
            return tagsRepository.save(tags1);
        }
        return tags.get();
    }
}
