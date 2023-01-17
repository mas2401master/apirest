package com.apirest.service;


import com.apirest.dto.HitResponse;
import com.apirest.entity.Hits;
import com.apirest.exception.ApiException;
import com.apirest.repository.HitsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class HitsServiceImp  implements  HitsService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private HitsRepository hitsRepository;

    @Override
    public Hits createHitsDataSource(Hits hits) {
        return hitsRepository.save(hits);
    }

    public Hits mapEntity(HitResponse hitResponse){
        Hits hits = modelMapper.map(hitResponse, Hits.class);
        return hits;
    }

    @Override
    public Page<Hits> findAll(Pageable page) {
        return hitsRepository.findAll(page);
    }

    @Override
    public Hits findById(Long id) {
        return hitsRepository.findById(id).get();
    }

    @Override
    public void deleteHits(Long id) {
        if(!hitsRepository.existsById(id)){
            throw new ApiException(HttpStatus.BAD_REQUEST,"Hits does not exist");
        }
        hitsRepository.deleteById(id);
    }

}
