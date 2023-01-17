package com.apirest.dto;

import lombok.Data;

@Data
public class AfterFetchResponse {
    public FormatResponse format;
    public int total;
}
