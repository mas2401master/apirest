package com.apirest.dto;


import lombok.Data;

import java.time.LocalDate;

@Data
public class HitsFilterDTO {
    public String author;
    public String title;
    public LocalDate date_min;
    public LocalDate date_max;
    public String tag;
    public String month;

    public LocalDate getDateMin(){
        if(month != ""){

            LocalDate today = LocalDate.now();
            int year = today.getYear();
            int day = 0;
            int monthNb =getMonthValue();
            if (monthNb > 0){
                date_min = LocalDate.of(year, monthNb, 1);
            }
        }
        return date_min;
    }

    public LocalDate getDateMax(){
        if(month != ""){
            LocalDate today = LocalDate.now();
            int year = today.getYear();
            int day = 0;
            int monthNb = getMonthValue();
            if (monthNb > 0){
                switch (monthNb) {
                    case 2 : if (today.isLeapYear()) day= 29; else day= 28;break;
                    case 4, 6, 9, 11: day=30; break;
                    default: day= 31;
                };
                date_max = LocalDate.of(year, monthNb, day);
            }
        }
        return  date_max;
    }

    public int getMonthValue(){
        int monthNb =0;
        if(month !=null){
            month = month.toLowerCase();
            if (month.equals("january"))
                monthNb = 1;
            else if(month.equals("february"))
                monthNb = 2;
            else if (month.equals("march"))
                monthNb = 3;
            else if (month.equals("april"))
                monthNb = 4;
            else if (month.equals("may"))
                monthNb = 5;
            else if (month.equals("june"))
                monthNb = 6;
            else if (month.equals("july"))
                monthNb = 7;
            else if (month.equals("august"))
                monthNb = 8;
            else if (month.equals("september"))
                monthNb = 9;
            else if (month.equals("october"))
                monthNb = 10;
            else if (month.equals("november"))
                monthNb = 11;
            else if (month.equals("december"))
                monthNb = 12;
        }
        return monthNb;
    }
}
