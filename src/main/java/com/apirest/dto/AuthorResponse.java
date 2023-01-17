package com.apirest.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class AuthorResponse {
    public String value;
    public String matchLevel;
    public ArrayList<String> matchedWords;
}
