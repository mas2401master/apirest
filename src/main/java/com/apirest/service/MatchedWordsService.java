package com.apirest.service;

import com.apirest.entity.MatchedWords;

import java.util.ArrayList;
import java.util.List;

public interface MatchedWordsService {
    List<MatchedWords> createMatchedWordsDataSource(ArrayList<String> matchedWords);
    MatchedWords createMatchedWords(String matchedWords);

}
