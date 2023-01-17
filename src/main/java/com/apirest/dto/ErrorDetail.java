package com.apirest.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class ErrorDetail {
    @Setter @Getter
    private Date datatime;
    @Setter @Getter
    private String message;
    @Setter @Getter
    private String detail;

    public ErrorDetail(Date date, String message, String description) {
        super();
        this.datatime =date;
        this.message = message;
        this.detail = description;
    }
}
