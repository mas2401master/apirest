package com.apirest.service;

import com.apirest.dto.DataResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class ApiRestDataServiceImp implements ApiRestDataService{

    @Value("${apirestdatasource.base-url}")
    protected String baseUrl;
    protected final RestTemplate restTemplate;

    public ApiRestDataServiceImp(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public DataResult getDataSource() {
        String uri = baseUrl + "/v1/search_by_date?query=java";
        ResponseEntity<DataResult> response = restTemplate.getForEntity(uri, DataResult.class);
        if(response.getStatusCode().is2xxSuccessful()){
            log.info("Status: "+response.getStatusCode());
            log.info("Data source loaded successfully!!!");
            return response.getBody();
        }
        log.error("Status: "+response.getStatusCode());
        log.error("Error getting data source from REST API...");
        throw new RuntimeException("Error");
    }

}
