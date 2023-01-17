package com.apirest.service;

import com.apirest.dto.*;
import com.apirest.entity.Highlight;
import com.apirest.entity.Hits;
import com.apirest.entity.MatchedWords;
import com.apirest.repository.HighlightRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class HighlightServiceImp implements HighlightService{
    @Autowired
    private HighlightRepository highlightRepository;

    @Autowired
    private MatchedWordsService matchedWordsService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void createHighlightDataSource(HighlightResultResponse highlightResultResponse, Hits hitsNew) {
        Highlight highlight = new Highlight();
        //Author
        AuthorResponse authorResponse = highlightResultResponse.getAuthor();
        if(authorResponse != null) {
            highlight = modelMapper.map(authorResponse, Highlight.class);
            saveHighlight(highlight , "author", hitsNew,authorResponse.getMatchedWords());
        }
        //CommentText
        CommentTextResponse commentTextResponse = highlightResultResponse.getComment_text();
        if(commentTextResponse != null) {
            highlight = modelMapper.map(commentTextResponse, Highlight.class);
            saveHighlight(highlight , "comment_text", hitsNew,commentTextResponse.getMatchedWords());
        }
        //storyTitleResponse
        StoryTitleResponse storyTitleResponse = highlightResultResponse.getStory_title();
        if(storyTitleResponse != null) {
            highlight = modelMapper.map(storyTitleResponse, Highlight.class);
            saveHighlight(highlight , "story_title", hitsNew,storyTitleResponse.getMatchedWords());
        }
        //storyUrlResponse
        StoryUrlResponse storyUrlResponse = highlightResultResponse.getStory_url();
        if(storyUrlResponse != null) {
            highlight = modelMapper.map(storyUrlResponse, Highlight.class);
            saveHighlight(highlight , "story_url", hitsNew,storyUrlResponse.getMatchedWords());
        }
        //titleResponse
        TitleResponse titleResponse = highlightResultResponse.getTitle();
        if(titleResponse != null) {
            highlight = modelMapper.map(titleResponse, Highlight.class);
            saveHighlight(highlight , "title", hitsNew,titleResponse.getMatchedWords());
        }
        //urlResponse
        UrlResponse urlResponse =highlightResultResponse.getUrl();
        if(urlResponse != null) {
            highlight = modelMapper.map(urlResponse, Highlight.class);
            saveHighlight(highlight , "url", hitsNew,urlResponse.getMatchedWords());
        }
        //storyTextResponse
        StoryTextResponse storyTextResponse = highlightResultResponse.getStory_text();
        if(storyTextResponse !=null){
            highlight = modelMapper.map(storyTextResponse, Highlight.class);
            saveHighlight(highlight , "story_text", hitsNew,storyTextResponse.getMatchedWords());
        }
    }

    public void saveHighlight(Highlight highlight , String type, Hits hitsNew, ArrayList<String> matchedList){
        List<MatchedWords> matchedWordsList;
        matchedWordsList = matchedWordsService.createMatchedWordsDataSource(matchedList);
        highlight.setType(type);
        highlight.setHits(hitsNew);
        highlight.setMatchedWord(matchedWordsList);
        highlightRepository.save(highlight);
        matchedWordsList.clear();
    }
}
