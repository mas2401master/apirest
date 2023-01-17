package com.apirest.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;

@Data
public class HitResponse {
    public LocalDate created_at;
    public String title;
    public String url;
    public String author;
    public int points;
    public String story_text;
    public String comment_text;
    public int num_comments;
    public int story_id;
    public String story_title;
    public String story_url;
    public int parent_id;
    public int created_at_i;
    public ArrayList<String> _tags;
    public String objectID;
    public HighlightResultResponse _highlightResult;
}
