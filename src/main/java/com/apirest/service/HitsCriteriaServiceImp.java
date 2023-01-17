package com.apirest.service;

import com.apirest.criteria.HitsCriteria;
import com.apirest.dto.HitsFilterDTO;
import com.apirest.entity.Hits;
import com.apirest.entity.Hits_;
import com.apirest.entity.Tags_;
import com.apirest.repository.HitsRepository;
import io.github.jhipster.service.QueryService;
import io.github.jhipster.service.filter.LocalDateFilter;
import io.github.jhipster.service.filter.StringFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.JoinType;

@Service
@Transactional(readOnly = true)
public class HitsCriteriaServiceImp extends QueryService<Hits> implements HitsCriteriaService{
    @Autowired
    private HitsRepository hitsRepository;

    @Override
    public Page<Hits> findByCriteria(Pageable page, HitsFilterDTO filter) {
        final Specification<Hits> specification = createSpecification(createCriteria(filter));
        Page<Hits> hitspage = hitsRepository.findAll(specification, page);
        return hitspage;
    }

    public HitsCriteria createCriteria(HitsFilterDTO hitsDTO) {
        HitsCriteria criteria = new HitsCriteria();
        if( hitsDTO != null){
            StringFilter filter = new StringFilter();
            if (!StringUtils.isAllBlank(hitsDTO.getAuthor())){
                filter.setContains(hitsDTO.getAuthor());
                criteria.setAuthor(filter);
            }
            if(!StringUtils.isAllBlank(hitsDTO.getTitle())){
                filter.setContains(hitsDTO.getTitle());
                criteria.setTitle(filter);
            }
            if(!StringUtils.isAllBlank(hitsDTO.getTag())){
                filter.setContains(hitsDTO.getTag());
                criteria.setTag(filter);
            }
            if(!StringUtils.isAllBlank(hitsDTO.getMonth())){
                LocalDateFilter dateFilter = new LocalDateFilter();
                dateFilter.setGreaterThanOrEqual(hitsDTO.getDateMin());
                criteria.setRangeCreated(dateFilter);
                dateFilter.setLessThanOrEqual(hitsDTO.getDateMax());
                criteria.setRangeCreated(dateFilter);
            }
        }
        return criteria;
    }

    private Specification<Hits> createSpecification(HitsCriteria filter) {
        Specification<Hits> specification = Specification.where(null);
        if (filter != null){
            if(filter.getAuthor() !=null){
                specification = specification.and(buildStringSpecification(filter.getAuthor(), Hits_.author));
            }
            if(filter.getTitle() != null){
                specification = specification.and(buildStringSpecification(filter.getTitle(),Hits_.title));
            }

            if (filter.getRangeCreated()!=null){
                specification = specification.and(buildRangeSpecification(filter.getRangeCreated(), Hits_.created_at));
            }

            if (filter.getTag() !=null){
                specification = specification.and(buildSpecification(filter.getTag(),
                        root ->root.join(Hits_.tags, JoinType.LEFT).get(Tags_.description)));
            }
        }
        return  specification;
    }
}
