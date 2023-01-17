package com.apirest.dto;

import lombok.Data;

import java.util.ArrayList;
@Data
public class StoryTextResponse {
    public String value;
    public String matchLevel;
    public boolean fullyHighlighted;
    public ArrayList<String> matchedWords;
}
