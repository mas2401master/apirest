package com.apirest.controller;

import com.apirest.dto.HitsFilterDTO;
import com.apirest.service.HitsCriteriaService;
import com.apirest.service.HitsService;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/hits")
public class HitsController {

    @Autowired
    private HitsService hitsService;
    @Autowired
    private HitsCriteriaService hitsCriteriaService;


    @GetMapping
    public ResponseEntity<?> listHitsFilter(@ParameterObject Pageable page, @ParameterObject HitsFilterDTO filterDTO){
        if (page !=null)
            page = PageRequest.of(0, 5);
        return ResponseEntity.status(HttpStatus.OK).body(hitsCriteriaService.findByCriteria(page,filterDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHits(@PathVariable (name = "id") long id){
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Hits deleted");
        hitsService.deleteHits(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }
}