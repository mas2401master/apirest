package com.apirest.dto;

import lombok.Data;

@Data
public class HighlightResultResponse {
    public AuthorResponse author;
    public CommentTextResponse comment_text;
    public StoryTitleResponse story_title;
    public StoryUrlResponse story_url;
    public TitleResponse title;
    public UrlResponse url;
    public StoryTextResponse story_text;
}
