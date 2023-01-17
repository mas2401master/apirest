package com.apirest.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "hits")
@Data
public class Hits {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    @Column(columnDefinition = "date")
    public LocalDate created_at;
    public String objectID;
    @Column (columnDefinition = "text")
    public String title;
    @Column (columnDefinition = "text")
    public String url;
    public String author;
    public int points;
    @Column (columnDefinition = "text")
    public String story_text;

    @Column (columnDefinition = "text")
    public String comment_text;
    public int num_comments;
    public int story_id;
    @Column (columnDefinition = "text")
    public String story_title;
    @Column (columnDefinition = "text")
    public String story_url;
    public int parent_id;
    public int created_at_i;


    @JoinTable(
            name = "tags_hits",
            joinColumns = @JoinColumn(name = "hits_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name="tags_id", nullable = false)
    )
    @ManyToMany(cascade = CascadeType.MERGE)
    public List<Tags> tags;

    @OneToMany(mappedBy = "hits", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Highlight> highlight;

}
