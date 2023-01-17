package com.apirest.service;

import com.apirest.entity.MatchedWords;
import com.apirest.repository.MatchedWordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MatchedWordsServiceImp implements MatchedWordsService {
    @Autowired
    private MatchedWordsRepository matchedWordsRepository;

    @Override
    public List<MatchedWords> createMatchedWordsDataSource(ArrayList<String> matchedWords) {
        List<MatchedWords> wordsList = new ArrayList<>();
        matchedWords.forEach(machedw->{
            if(machedw !=""){
                MatchedWords matchedWords1 = new MatchedWords();
                matchedWords1 = createMatchedWords(machedw);
                wordsList.add(matchedWords1);
            }
        });
        return wordsList;
    }
    @Override
    public MatchedWords createMatchedWords(String matched) {
        Optional<MatchedWords> matchedWords = matchedWordsRepository.findByDescription(matched);
        if(matchedWords.isEmpty()){
            MatchedWords matchedWords1 = new MatchedWords();
            matchedWords1.setDescription(matched);
            return matchedWordsRepository.save(matchedWords1);
        }
        return matchedWords.get();
    }
}
