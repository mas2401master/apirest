package com.apirest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "highlight" )
@Data
public class Highlight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;
    private String type;

    @Column (columnDefinition = "text")
    private String value;
    private String matchLevel;
    public boolean fullyHighlighted;

    @JoinTable(
            name = "matched_words_highlight",
            joinColumns = @JoinColumn(name = "highlight_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name="matched_words_id", nullable = false)
    )
    @ManyToMany(cascade = CascadeType.MERGE)
    public List<MatchedWords> matchedWord;

    @ManyToOne(optional = false)
    @JsonIgnoreProperties("hits")
    @JsonIgnore
    private Hits hits;

    public void addMatchedWords(MatchedWords matched){
        if(this.matchedWord == null){
            this.matchedWord = new ArrayList<>();
        }
        this.matchedWord.add(matched);
    }
}
