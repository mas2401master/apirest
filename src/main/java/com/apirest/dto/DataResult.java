package com.apirest.dto;

import lombok.Data;

import java.util.ArrayList;
@Data
public class DataResult {
    public ArrayList<HitResponse> hits;
    public int nbHits;
    public int page;
    public int nbPages;
    public int hitsPerPage;
    public boolean exhaustiveNbHits;
    public boolean exhaustiveTypo;
    public ExhaustiveResponse exhaustive;
    public String query;
    public String params;
    public int processingTimeMS;
    public ProcessingTimingsMS processingTimingsMS;
}
