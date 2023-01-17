package com.apirest.criteria;

import io.github.jhipster.service.filter.LocalDateFilter;
import io.github.jhipster.service.filter.StringFilter;
import lombok.Data;

@Data
public class HitsCriteria {
    public StringFilter author;
    public StringFilter title;
    public LocalDateFilter rangeCreated;
    public StringFilter tag;
}
