package com.armorbldgsolutions.products.service;

import com.armorbldgsolutions.products.domain.PartDto;
import com.armorbldgsolutions.products.repository.PartsRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PartsSearchService {

    private final PartsRepository partsRepository;

    public PartsSearchService(PartsRepository partsRepository) {
        this.partsRepository = partsRepository;
    }

    public Collection<PartDto> findAllParts() {

        return partsRepository.findAllParts();
    }
}
