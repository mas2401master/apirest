package com.apirest.dto;

import lombok.Data;

import java.util.ArrayList;
@Data
public class StoryTitleResponse {
    public String value;
    public String matchLevel;
    public ArrayList<String> matchedWords;
    public boolean fullyHighlighted;
}
