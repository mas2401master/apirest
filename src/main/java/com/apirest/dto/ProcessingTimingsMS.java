package com.apirest.dto;

import lombok.Data;

@Data
public class ProcessingTimingsMS {
    public AfterFetchResponse afterFetch;
    public FetchResponse fetch;
    public int total;
}
