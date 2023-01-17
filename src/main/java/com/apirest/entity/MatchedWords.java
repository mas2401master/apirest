package com.apirest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "matched_words", uniqueConstraints = {@UniqueConstraint(columnNames = {"description"}) })
@Data
public class MatchedWords {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Highlight> highlight;
}
/*
drop table matched_words_highlight;
drop table matched_words;
drop table tags_hits;

drop table tags;
drop table highlight;
drop table hits;

select * from tags;
select * from tags_hits*/